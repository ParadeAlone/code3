package com.it.controller;

import com.it.entity.Inventory;
import com.it.service.InventoryService;
import org.springframework.web.bind.annotation.*;
import com.it.common.ReturnData;
import com.it.common.page.PageResult;
import java.util.List;

import javax.annotation.Resource;


@RestController
@RequestMapping("/inventory")
public class InventoryController {
    /**
     * 服务对象
     */
    @Resource
    private InventoryService inventoryService;

    /**
     * 通过主键查询单条数据
     *
     * @param inventory 参数对象
     * @return 单条数据
     */
    @RequestMapping(value = "get", method = RequestMethod.POST)
    public ReturnData<Inventory> selectOne(Inventory inventory) {
        Inventory result = inventoryService.selectById(inventory.getId());
        if(result != null){
            return ReturnData.GET_SUCCESS(result);
        }
        return ReturnData.GET_FAIL();
    }
    
    /**
     * 新增一条数据
     *
     * @param inventory 实体类
     * @return Response对象
     */
    @RequestMapping(value = "insert", method = RequestMethod.POST)
    public ReturnData<Inventory> insert(Inventory inventory) {
        int result = inventoryService.insert(inventory);
        if (result > 0) {
            return ReturnData.ADD_SUCCESS();
        }
        return ReturnData.ADD_FAIL();
    }

    /**
     * 修改一条数据
     *
     * @param inventory 实体类
     * @return Response对象
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public ReturnData<Inventory> update(Inventory inventory) {
        Inventory result = inventoryService.update(inventory);
        if (result != null) {
            return ReturnData.UPDATE_SUCCESS(result);
        }
        return ReturnData.UPDATE_FAIL();
    }

    /**
     * 删除一条数据
     *
     * @param inventory 参数对象
     * @return Response对象
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public ReturnData<Inventory> delete(Inventory inventory) {
        int result = inventoryService.deleteById(inventory.getId());
        if (result > 0) {
            return ReturnData.DELETE_SUCCESS();
        }
        return ReturnData.DELETE_FAIL();
    }

    /**
     * 查询全部
     *
     * @return Response对象
     */
    @RequestMapping(value = "selectAll", method = RequestMethod.POST)
    public ReturnData<List<Inventory>> selectAll(Inventory inventory) {
        List<Inventory> inventorys = inventoryService.selectList(inventory);
        if (inventorys != null) {
            return ReturnData.GET_SUCCESS(inventorys);
        }
        return ReturnData.GET_FAIL();
    }

    /**
     * 分页查询
     *
     * @param start 偏移
     * @param limit 条数
     * @return Response对象
     */
    @RequestMapping(value = "selectPage", method = RequestMethod.POST)
    public ReturnData<PageResult> selectPage(Integer start, Integer limit, Inventory inventory) {
        PageResult pageResult = inventoryService.selectPage(start, limit, inventory);
        if (pageResult != null) {
            return ReturnData.GET_SUCCESS(pageResult);
        }
        return ReturnData.GET_FAIL();
    }
    
}


