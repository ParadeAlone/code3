package com.it.service;

import com.it.entity.Comment;
import java.util.List;
import java.util.Map;
import com.it.common.page.PageResult;


public interface CommentService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Comment selectById(Integer id);

    /**
     * 分页查询
     *
     * @param start 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    PageResult selectPage(int start, int limit, Comment comment);

    /**                                                                        
     * 查询全部
     *
     * @return 对象列表
     */
    List<Comment> selectAll(Comment comment);
    
    /**
     * 通过实体作为筛选条件查询
     *
     * @param comment 实例对象
     * @return 对象列表
     */
    List<Comment> selectList(Comment comment);

    /**
     * 新增数据
     *
     * @param comment 实例对象
     * @return 影响行数
     */
    int insert(Comment comment);
	
	/**
     * 批量新增
     *
     * @param comments 实例对象的集合
     * @return 影响行数
     */
	int batchInsert(List<Comment> comments);
	
    /**
     * 修改数据
     *
     * @param comment 实例对象
     * @return 修改
     */
    Comment update(Comment comment);

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


