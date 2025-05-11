package com.it.service.impl;

import com.it.entity.Product;
import com.it.dao.ProductDAO;
import com.it.service.ProductService;
import org.springframework.stereotype.Service;
import com.it.common.page.PageResult;
import com.it.common.page.PageRequest;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.it.common.util.PageUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


@Service("productService")
public class ProductServiceImpl implements ProductService {
    @Resource
    private ProductDAO productDAO;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Product selectById(Integer id) {
        return this.productDAO.selectById(id);
    }

    /**
     * 分页查询
     *
     * @param start 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public PageResult selectPage(int start, int limit, Product product) {
        PageRequest pageRequest=new PageRequest(start,limit);
        return PageUtils.getPageResult(pageRequest, getPageInfo(pageRequest,product));
    }

    /**
     * 调用分页插件完成分页
     * @param pageRequest
     * @return
     */
    private PageInfo<Product> getPageInfo(PageRequest pageRequest, Product product) {
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();
        PageHelper.startPage(pageNum, pageSize);
        List<Product> products = this.productDAO.selectList(product);
        return new PageInfo<>(products);
    }

    /**
     * 查询所有
     *
     * @return 实例对象的集合
     */
    @Override
    public List<Product> selectAll( Product product) {
        return this.productDAO.selectAll(product);
    }
    
    /**
     * 根据条件查询
     *
     * @return 实例对象的集合
     */
    @Override
    public List<Product> selectList(Product product) {
        return this.productDAO.selectList(product);
    }
    
    /**
     * 新增数据
     *
     * @param product 实例对象
     * @return 实例对象
     */
    @Override
    public int insert(Product product) {
        return this.productDAO.insert(product);
    }

    /**
     * 批量新增
     *
     * @param products 实例对象的集合
     * @return 生效的条数
     */
    @Override
    public int batchInsert(List<Product> products) {
        return this.productDAO.batchInsert(products);
    }

    /**
     * 修改数据
     *
     * @param product 实例对象
     * @return 实例对象
     */
    @Override
    public Product update(Product product) {
        this.productDAO.update(product);
        return this.selectById(product.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public int deleteById(Integer id) {
        return this.productDAO.deleteById(id);
    }
    
    /**
     * 查询总数据数
     *
     * @return 数据总数
     */
    @Override
    public int count(){
        return this.productDAO.count();
    }
}


