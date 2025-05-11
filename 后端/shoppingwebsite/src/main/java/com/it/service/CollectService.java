package com.it.service;

import com.it.entity.Collect;
import java.util.List;
import java.util.Map;
import com.it.common.page.PageResult;


public interface CollectService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Collect selectById(Integer id);

    /**
     * 分页查询
     *
     * @param start 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    PageResult selectPage(int start, int limit, Collect collect);

    /**                                                                        
     * 查询全部
     *
     * @return 对象列表
     */
    List<Collect> selectAll(Collect collect);
    
    /**
     * 通过实体作为筛选条件查询
     *
     * @param collect 实例对象
     * @return 对象列表
     */
    List<Collect> selectList(Collect collect);

    /**
     * 新增数据
     *
     * @param collect 实例对象
     * @return 影响行数
     */
    int insert(Collect collect);
	
	/**
     * 批量新增
     *
     * @param collects 实例对象的集合
     * @return 影响行数
     */
	int batchInsert(List<Collect> collects);
	
    /**
     * 修改数据
     *
     * @param collect 实例对象
     * @return 修改
     */
    Collect update(Collect collect);

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


