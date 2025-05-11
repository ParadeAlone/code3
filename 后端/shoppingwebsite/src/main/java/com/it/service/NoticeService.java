package com.it.service;

import com.it.entity.Notice;
import java.util.List;
import java.util.Map;
import com.it.common.page.PageResult;


public interface NoticeService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Notice selectById(Integer id);

    /**
     * 分页查询
     *
     * @param start 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    PageResult selectPage(int start, int limit, Notice notice);

    /**                                                                        
     * 查询全部
     *
     * @return 对象列表
     */
    List<Notice> selectAll(Notice notice);
    
    /**
     * 通过实体作为筛选条件查询
     *
     * @param notice 实例对象
     * @return 对象列表
     */
    List<Notice> selectList(Notice notice);

    /**
     * 新增数据
     *
     * @param notice 实例对象
     * @return 影响行数
     */
    int insert(Notice notice);
	
	/**
     * 批量新增
     *
     * @param notices 实例对象的集合
     * @return 影响行数
     */
	int batchInsert(List<Notice> notices);
	
    /**
     * 修改数据
     *
     * @param notice 实例对象
     * @return 修改
     */
    Notice update(Notice notice);

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


