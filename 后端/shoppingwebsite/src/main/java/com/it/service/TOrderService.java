package com.it.service;

import com.it.entity.TOrder;
import java.util.List;
import java.util.Map;
import com.it.common.page.PageResult;


public interface TOrderService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    TOrder selectById(Integer id);

    /**
     * 分页查询
     *
     * @param start 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    PageResult selectPage(int start, int limit, TOrder tOrder);

    /**                                                                        
     * 查询全部
     *
     * @return 对象列表
     */
    List<TOrder> selectAll(TOrder tOrder);
    
    /**
     * 通过实体作为筛选条件查询
     *
     * @param tOrder 实例对象
     * @return 对象列表
     */
    List<TOrder> selectList(TOrder tOrder);

    /**
     * 新增数据
     *
     * @param tOrder 实例对象
     * @return 影响行数
     */
    int insert(TOrder tOrder);
	
	/**
     * 批量新增
     *
     * @param tOrders 实例对象的集合
     * @return 影响行数
     */
	int batchInsert(List<TOrder> tOrders);
	
    /**
     * 修改数据
     *
     * @param tOrder 实例对象
     * @return 修改
     */
    TOrder update(TOrder tOrder);

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

    /**
     * 营业额
     * @param tOrder
     * @return
     */

    List<TOrder> selectSellerMoney(TOrder tOrder);
}


