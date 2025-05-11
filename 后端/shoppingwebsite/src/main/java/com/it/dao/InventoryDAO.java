package com.it.dao;

import com.it.entity.Inventory;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;


public interface InventoryDAO {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Inventory selectById(Integer id);
	
    /**
     * 分页查询
     *
     * @param start 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Inventory> selectPage(@Param("start") int start, @Param("limit") int limit);

    /**
     * 查询全部
     *
     * @return 对象列表
     */
    List<Inventory> selectAll(Inventory inventory);
    
    /**
     * 通过实体作为筛选条件查询
     *
     * @param inventory 实例对象
     * @return 对象列表
     */
    List<Inventory> selectList(Inventory inventory);

    /**
     * 新增数据
     *
     * @param inventory 实例对象
     * @return 影响行数
     */
    int insert(Inventory inventory);
	
	/**
     * 批量新增
     *
     * @param inventorys 实例对象的集合
     * @return 影响行数
     */
	int batchInsert(List<Inventory> inventorys);
	
    /**
     * 修改数据
     *
     * @param inventory 实例对象
     * @return 影响行数
     */
    int update(Inventory inventory);

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


