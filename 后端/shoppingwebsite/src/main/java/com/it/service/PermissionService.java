package com.it.service;

import com.it.entity.Permission;
import java.util.List;
import java.util.Map;
import com.it.common.page.PageResult;


public interface PermissionService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Permission selectById(Integer id);

    /**
     * 分页查询
     *
     * @param start 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    PageResult selectPage(int start, int limit, Permission permission);

    /**                                                                        
     * 查询全部
     *
     * @return 对象列表
     */
    List<Permission> selectAll(Permission permission);
    
    /**
     * 通过实体作为筛选条件查询
     *
     * @param permission 实例对象
     * @return 对象列表
     */
    List<Permission> selectList(Permission permission);



    /**
     * 通过实体作为筛选条件查询
     *
     * @param permission 实例对象
     * @return 对象列表
     */
    List<Permission> selectDistinct(Permission permission);



    /**
     * 新增数据
     *
     * @param permission 实例对象
     * @return 影响行数
     */
    int insert(Permission permission, String arr, String arr1);
	
	/**
     * 批量新增
     *
     * @param permissions 实例对象的集合
     * @return 影响行数
     */
	int batchInsert(List<Permission> permissions);
	
    /**
     * 修改数据
     *
     * @param permission 实例对象
     * @return 修改
     */
    Permission update(Permission permission);

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


