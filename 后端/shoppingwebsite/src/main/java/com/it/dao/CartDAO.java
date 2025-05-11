package com.it.dao;

import com.it.entity.Cart;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;


public interface CartDAO {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Cart selectById(Integer id);
	
    /**
     * 分页查询
     *
     * @param start 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Cart> selectPage(@Param("start") int start, @Param("limit") int limit);

    /**
     * 查询全部
     *
     * @return 对象列表
     */
    List<Cart> selectAll(Cart cart);
    
    /**
     * 通过实体作为筛选条件查询
     *
     * @param cart 实例对象
     * @return 对象列表
     */
    List<Cart> selectList(Cart cart);

    /**
     * 新增数据
     *
     * @param cart 实例对象
     * @return 影响行数
     */
    int insert(Cart cart);
	
	/**
     * 批量新增
     *
     * @param carts 实例对象的集合
     * @return 影响行数
     */
	int batchInsert(List<Cart> carts);
	
    /**
     * 修改数据
     *
     * @param cart 实例对象
     * @return 影响行数
     */
    int update(Cart cart);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    /**
     * 查询总数据数
     *
     * @return 数据总数
     */
    int count();

    List<Cart> selectGroupBy(Cart cart2);
}


