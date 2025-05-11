package com.it.controller;

import com.it.dao.TDetailDAO;
import com.it.entity.*;
import com.it.service.*;
import com.it.util.CheckCode;
import com.it.util.Info;
import org.springframework.web.bind.annotation.*;
import com.it.common.ReturnData;
import com.it.common.page.PageResult;

import java.util.List;

import javax.annotation.Resource;


@RestController
@RequestMapping("/tOrder")
public class TOrderController {
    /**
     * 服务对象
     */
    @Resource
    private TOrderService tOrderService;
    @Resource
    private CartService cartService;
    @Resource
    private ProductService productService;
    @Resource
    private TDetailService tDetailService;
    @Resource
    private InventoryService inventoryService;
    @Resource
    private AddressService addressService;




    /**
     * 通过主键查询单条数据
     *
     * @param tOrder 参数对象
     * @return 单条数据
     */
    @RequestMapping(value = "get", method = RequestMethod.POST)
    public ReturnData<TOrder> selectOne(TOrder tOrder) {
        TOrder result = tOrderService.selectById(tOrder.getId());
        if (result != null) {
            return ReturnData.GET_SUCCESS(result);
        }
        return ReturnData.GET_FAIL();
    }

    /**
     * 新增一条数据
     *
     * @param tOrder 实体类
     * @return Response对象
     */
    @RequestMapping(value = "insert", method = RequestMethod.POST)
    public ReturnData<TOrder> insert(TOrder tOrder) {
        Cart cart2 = new Cart();
        cart2.setMemberid(tOrder.getMemberid());
        List<Cart> cartList2 = cartService.selectGroupBy(cart2);
        for (Cart cart : cartList2) {
            cart2.setSellerid(cart.getSellerid());
            List<Cart> cartList1 = cartService.selectList(cart2);
            String ddno = new CheckCode().getYzm();
            String productInfo = "";
            Double total = 0D;
            for (Cart cart3 : cartList1) {
                Double price = cart3.getPrice();
                if(cart3.getTprice()>0){
                    price = cart3.getTprice();
                }
                total+= price * cart3.getNum();
                productInfo += cart3.getProductName() + " * " + cart3.getNum() + "<br/>";
                TDetail tDetail = new TDetail();
                tDetail.setDdno(ddno);
                tDetail.setMemberid(tOrder.getMemberid());
                tDetail.setProductid(cart3.getProductid());
                tDetail.setNum(cart3.getNum());
                tDetail.setStatus(tOrder.getStatus());
                tDetail.setSavetime(Info.getDateStr().substring(0,10 ));
                tDetailService.insert(tDetail);
                cartService.deleteById(cart3.getId());
            }
            TOrder tOrder1 = new TOrder();
            tOrder1.setMemberid(tOrder.getMemberid());
            tOrder1.setDdno(ddno);
            tOrder1.setTotal(Double.parseDouble(String.format("%.2f",total)));
            tOrder1.setProductInfo(productInfo);
            tOrder1.setSavetime(Info.getDateStr());
            tOrder1.setStatus(tOrder.getStatus());
            tOrder1.setSellerid(cart.getSellerid());
            Address address = addressService.selectById(tOrder.getAddressid());
            tOrder1.setAddress(address.getAddr() +" " +address.getName() +" "+ address.getTel());
            tOrderService.insert(tOrder1);
        }
        return ReturnData.ADD_SUCCESS();
    }


    /**
     * 修改一条数据
     *
     * @param tOrder 实体类
     * @return Response对象
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public ReturnData<TOrder> update(TOrder tOrder) {
        TOrder result = tOrderService.update(tOrder);

        TDetail tDetail = new TDetail();
        tDetail.setDdno(result.getDdno());
        List<TDetail> tDetailList = tDetailService.selectList(tDetail);
        for (TDetail detail : tDetailList) {
            detail.setStatus(tOrder.getStatus());
            tDetailService.update(detail);

            if(tOrder.getStatus().equals("已退款")){
                Inventory inventory = new Inventory();
                inventory.setProductid(detail.getProductid());
                inventory.setFlag("in");
                inventory.setNum(detail.getNum());
                inventory.setSavetime(Info.getDateStr().substring(0,10));
                inventory.setDelstatus("0");
                inventoryService.insert(inventory);
            }
        }
        if (result != null) {
            return ReturnData.UPDATE_SUCCESS(result);
        }
        return ReturnData.UPDATE_FAIL();
    }

    /**
     * 删除一条数据
     *
     * @param tOrder 参数对象
     * @return Response对象
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public ReturnData<TOrder> delete(TOrder tOrder) {
        int result = tOrderService.deleteById(tOrder.getId());
        if (result > 0) {
            return ReturnData.DELETE_SUCCESS();
        }
        return ReturnData.DELETE_FAIL();
    }

    /**
     * 查询全部
     *
     * @return Response对象
     */
    @RequestMapping(value = "selectAll", method = RequestMethod.POST)
    public ReturnData<List<TOrder>> selectAll(TOrder tOrder) {
        List<TOrder> tOrders = tOrderService.selectList(tOrder);
        if (tOrders != null) {
            return ReturnData.GET_SUCCESS(tOrders);
        }
        return ReturnData.GET_FAIL();
    }

    /**
     * 分页查询
     *
     * @param start 偏移
     * @param limit 条数
     * @return Response对象
     */
    @RequestMapping(value = "selectPage", method = RequestMethod.POST)
    public ReturnData<PageResult> selectPage(Integer start, Integer limit, TOrder tOrder) {
        PageResult pageResult = tOrderService.selectPage(start, limit, tOrder);
        if (pageResult != null) {
            return ReturnData.GET_SUCCESS(pageResult);
        }
        return ReturnData.GET_FAIL();
    }

    /**
     * 发货
     * @param tOrder 实体类
     * @return Response对象
     */
    @RequestMapping(value = "send", method = RequestMethod.POST)
    public ReturnData<TOrder> send(TOrder tOrder) {
        TDetail tDetail = new TDetail();
        tDetail.setDdno(tOrder.getDdno());
        List<TDetail> tDetailList = tDetailService.selectList(tDetail);
        boolean flag = true;
        for (TDetail detail : tDetailList) {
            Product prouct = productService.selectById(detail.getProductid());
            if (prouct.getStockNum() < detail.getNum()) {
                flag = false;
                break;
            }
        }
        if (flag == true) {
            TOrder result = tOrderService.update(tOrder);
            for (TDetail detail : tDetailList) {
                Inventory inventory = new Inventory();
                inventory.setProductid(detail.getProductid());
                inventory.setNum(detail.getNum());
                inventory.setFlag("out");
                inventory.setSavetime(Info.getDateStr().substring(0, 10));
                inventory.setDelstatus("0");
                inventoryService.insert(inventory);

                detail.setStatus(tOrder.getStatus());
                tDetailService.update(detail);
            }
            return ReturnData.UPDATE_SUCCESS(result);
        }
        return ReturnData.UPDATE_FAIL();
    }


    /**
     * 交易完成
     * @param tOrder 实体类
     * @return Response对象
     */
    @RequestMapping(value = "done", method = RequestMethod.POST)
    public ReturnData<TOrder> done(TOrder tOrder) {
        TDetail tDetail = new TDetail();
        tDetail.setDdno(tOrder.getDdno());
        List<TDetail> tDetailList = tDetailService.selectList(tDetail);
        for (TDetail detail : tDetailList) {
            detail.setStatus(tOrder.getStatus());
            tDetailService.update(detail);
        }
        TOrder result = tOrderService.update(tOrder);
        return ReturnData.UPDATE_SUCCESS(result);
    }

}


