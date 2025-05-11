package com.it.service.impl;

import com.it.entity.Category;
import com.it.dao.CategoryDAO;
import com.it.service.CategoryService;
import org.springframework.stereotype.Service;
import com.it.common.page.PageResult;
import com.it.common.page.PageRequest;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.it.common.util.PageUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {
    @Resource
    private CategoryDAO categoryDAO;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Category selectById(Integer id) {
        return this.categoryDAO.selectById(id);
    }

    /**
     * 分页查询
     *
     * @param start 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public PageResult selectPage(int start, int limit, Category category) {
        PageRequest pageRequest=new PageRequest(start,limit);
        return PageUtils.getPageResult(pageRequest, getPageInfo(pageRequest,category));
    }

    /**
     * 调用分页插件完成分页
     * @param pageRequest
     * @return
     */
    private PageInfo<Category> getPageInfo(PageRequest pageRequest, Category category) {
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();
        PageHelper.startPage(pageNum, pageSize);
        List<Category> categorys = this.categoryDAO.selectList(category);
        for (Category category1 : categorys) {
            Category category2 = new Category();
            category2.setFatherid(category1.getId());
            List<Category> categories = categoryDAO.selectList(category2);
            category1.setCategories(categories);
        }
        return new PageInfo<>(categorys);
    }

    /**
     * 查询所有
     *
     * @return 实例对象的集合
     */
    @Override
    public List<Category> selectAll( Category category) {
        return this.categoryDAO.selectAll(category);
    }
    
    /**
     * 根据条件查询
     *
     * @return 实例对象的集合
     */
    @Override
    public List<Category> selectList(Category category) {
        List<Category> categories = this.categoryDAO.selectList(category);
        for (Category category1 : categories) {
            Category category2 = new Category();
            category2.setFatherid(category1.getId());
            List<Category> categories1 = categoryDAO.selectList(category2);
            category1.setCategories(categories1);
        }
        return categories;
    }
    
    /**
     * 新增数据
     *
     * @param category 实例对象
     * @return 实例对象
     */
    @Override
    public int insert(Category category) {
        return this.categoryDAO.insert(category);
    }

    /**
     * 批量新增
     *
     * @param categorys 实例对象的集合
     * @return 生效的条数
     */
    @Override
    public int batchInsert(List<Category> categorys) {
        return this.categoryDAO.batchInsert(categorys);
    }

    /**
     * 修改数据
     *
     * @param category 实例对象
     * @return 实例对象
     */
    @Override
    public Category update(Category category) {
        this.categoryDAO.update(category);
        return this.selectById(category.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public int deleteById(Integer id) {
        return this.categoryDAO.deleteById(id);
    }
    
    /**
     * 查询总数据数
     *
     * @return 数据总数
     */
    @Override
    public int count(){
        return this.categoryDAO.count();
    }
}


