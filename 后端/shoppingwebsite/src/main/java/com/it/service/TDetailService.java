package com.it.service;

import com.it.entity.TDetail;
import java.util.List;
import java.util.Map;
import com.it.common.page.PageResult;


public interface TDetailService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    TDetail selectById(Integer id);

    /**
     * 分页查询
     *
     * @param start 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    PageResult selectPage(int start, int limit, TDetail tDetail);

    /**                                                                        
     * 查询全部
     *
     * @return 对象列表
     */
    List<TDetail> selectAll(TDetail tDetail);
    
    /**
     * 通过实体作为筛选条件查询
     *
     * @param tDetail 实例对象
     * @return 对象列表
     */
    List<TDetail> selectList(TDetail tDetail);

    /**
     * 新增数据
     *
     * @param tDetail 实例对象
     * @return 影响行数
     */
    int insert(TDetail tDetail);
	
	/**
     * 批量新增
     *
     * @param tDetails 实例对象的集合
     * @return 影响行数
     */
	int batchInsert(List<TDetail> tDetails);
	
    /**
     * 修改数据
     *
     * @param tDetail 实例对象
     * @return 修改
     */
    TDetail update(TDetail tDetail);

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
}


