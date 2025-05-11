package com.it.controller;

import com.it.entity.Cart;
import com.it.service.CartService;
import org.springframework.web.bind.annotation.*;
import com.it.common.ReturnData;
import com.it.common.page.PageResult;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;


@RestController
@RequestMapping("/cart")
public class CartController {
    /**
     * 服务对象
     */
    @Resource
    private CartService cartService;

    /**
     * 通过主键查询单条数据
     *
     * @param cart 参数对象
     * @return 单条数据
     */
    @RequestMapping(value = "get", method = RequestMethod.POST)
    public ReturnData<Cart> selectOne(Cart cart) {
        Cart result = cartService.selectById(cart.getId());
        if(result != null){
            return ReturnData.GET_SUCCESS(result);
        }
        return ReturnData.GET_FAIL();
    }
    
    /**
     * 新增一条数据
     *
     * @param cart 实体类
     * @return Response对象
     */
    @RequestMapping(value = "insert", method = RequestMethod.POST)
    public ReturnData<Cart> insert(Cart cart) {
        Cart cart1 = new Cart();
        cart1.setMemberid(cart.getMemberid());
        cart1.setProductid(cart.getProductid());
        List<Cart> cartList = cartService.selectList(cart1);
        if (cartList.size() == 0) {
            int result = cartService.insert(cart);
        }else{
            Cart cart2 = cartList.get(0);
            cart2.setNum(cart2.getNum()+cart.getNum());
            cartService.update(cart2);
        }
        return ReturnData.ADD_FAIL();
    }

    /**
     * 修改一条数据
     *
     * @param cart 实体类
     * @return Response对象
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public ReturnData<Cart> update(Cart cart) {
        Cart result = cartService.update(cart);
        if (result != null) {
            return ReturnData.UPDATE_SUCCESS(result);
        }
        return ReturnData.UPDATE_FAIL();
    }

    /**
     * 删除一条数据
     *
     * @param cart 参数对象
     * @return Response对象
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public ReturnData<Cart> delete(Cart cart) {
        int result = cartService.deleteById(cart.getId());
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
    public HashMap<String,Object> selectAll(Cart cart) {
        HashMap<String,Object> res = new HashMap<String,Object>();
        List<Cart> carts = cartService.selectList(cart);
        Double total = 0D;
        for (Cart cart1 : carts) {
            total+= cart1.getSubTotal();
        }
        res.put("data", carts);
        res.put("total", total);
        return res;
    }

    /**
     * 分页查询
     *
     * @param start 偏移
     * @param limit 条数
     * @return Response对象
     */
    @RequestMapping(value = "selectPage", method = RequestMethod.POST)
    public ReturnData<PageResult> selectPage(Integer start, Integer limit, Cart cart) {
        PageResult pageResult = cartService.selectPage(start, limit, cart);
        if (pageResult != null) {
            return ReturnData.GET_SUCCESS(pageResult);
        }
        return ReturnData.GET_FAIL();
    }
    
}


