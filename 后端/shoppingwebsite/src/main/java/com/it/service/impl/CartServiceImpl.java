package com.it.service.impl;

import com.it.entity.Cart;
import com.it.dao.CartDAO;
import com.it.service.CartService;
import org.springframework.stereotype.Service;
import com.it.common.page.PageResult;
import com.it.common.page.PageRequest;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.it.common.util.PageUtils;

import javax.annotation.Resource;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;


@Service("cartService")
public class CartServiceImpl implements CartService {
    @Resource
    private CartDAO cartDAO;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Cart selectById(Integer id) {
        return this.cartDAO.selectById(id);
    }

    /**
     * 分页查询
     *
     * @param start 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public PageResult selectPage(int start, int limit, Cart cart) {
        PageRequest pageRequest=new PageRequest(start,limit);
        return PageUtils.getPageResult(pageRequest, getPageInfo(pageRequest,cart));
    }

    /**
     * 调用分页插件完成分页
     * @param pageRequest
     * @return
     */
    private PageInfo<Cart> getPageInfo(PageRequest pageRequest, Cart cart) {
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();
        PageHelper.startPage(pageNum, pageSize);
        List<Cart> carts = this.cartDAO.selectList(cart);
        return new PageInfo<>(carts);
    }

    /**
     * 查询所有
     *
     * @return 实例对象的集合
     */
    @Override
    public List<Cart> selectAll( Cart cart) {
        return this.cartDAO.selectAll(cart);
    }
    
    /**
     * 根据条件查询
     *
     * @return 实例对象的集合
     */
    @Override
    public List<Cart> selectList(Cart cart) {
        List<Cart> cartList =  this.cartDAO.selectList(cart);
        for (Cart cart1 : cartList) {
            Double subTotal = 0.0;
            Double price = cart1.getPrice();
            if(cart1.getTprice()>0){
                price = cart1.getTprice();
            }
            subTotal = price * cart1.getNum();
            cart1.setSubTotal(Double.parseDouble(String.format("%.2f", subTotal)));
        }
        return cartList;
    }
    
    /**
     * 新增数据
     *
     * @param cart 实例对象
     * @return 实例对象
     */
    @Override
    public int insert(Cart cart) {
        return this.cartDAO.insert(cart);
    }

    /**
     * 批量新增
     *
     * @param carts 实例对象的集合
     * @return 生效的条数
     */
    @Override
    public int batchInsert(List<Cart> carts) {
        return this.cartDAO.batchInsert(carts);
    }

    /**
     * 修改数据
     *
     * @param cart 实例对象
     * @return 实例对象
     */
    @Override
    public Cart update(Cart cart) {
        this.cartDAO.update(cart);
        return this.selectById(cart.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public int deleteById(Integer id) {
        return this.cartDAO.deleteById(id);
    }
    
    /**
     * 查询总数据数
     *
     * @return 数据总数
     */
    @Override
    public int count(){
        return this.cartDAO.count();
    }

    @Override
    public List<Cart> selectGroupBy(Cart cart2) {
        return this.cartDAO.selectGroupBy(cart2);
    }

}


