package com.it.controller;

import com.it.entity.Permission;
import com.it.service.PermissionService;
import org.springframework.web.bind.annotation.*;
import com.it.common.ReturnData;
import com.it.common.page.PageResult;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;


@RestController
@RequestMapping("/permission")
public class PermissionController {
    /**
     * 服务对象
     */
    @Resource
    private PermissionService permissionService;

    /**
     * 通过主键查询单条数据
     *
     * @param permission 参数对象
     * @return 单条数据
     */
    @RequestMapping(value = "get", method = RequestMethod.POST)
    public ReturnData<Permission> selectOne(Permission permission) {
        Permission result = permissionService.selectById(permission.getId());
        if(result != null){
            return ReturnData.GET_SUCCESS(result);
        }
        return ReturnData.GET_FAIL();
    }

    /**
     * 新增一条数据
     *
     * @param permission 实体类
     * @return Response对象
     */
    @RequestMapping(value = "insert", method = RequestMethod.POST)
    public ReturnData<Permission> insert(Permission permission,String arr,String arr1) {
        int result = permissionService.insert(permission,arr,arr1);
        if (result > 0) {
            return ReturnData.ADD_SUCCESS();
        }
        return ReturnData.ADD_FAIL();
    }
    /**
     * 修改一条数据
     *
     * @param permission 实体类
     * @return Response对象
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public ReturnData<Permission> update(Permission permission) {
        Permission result = permissionService.update(permission);
        if (result != null) {
            return ReturnData.UPDATE_SUCCESS(result);
        }
        return ReturnData.UPDATE_FAIL();
    }

    /**
     * 删除一条数据
     *
     * @param permission 参数对象
     * @return Response对象
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public ReturnData<Permission> delete(Permission permission) {
        int result = permissionService.deleteById(permission.getId());
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
    public ReturnData<List<Permission>> selectAll(Permission permission) {
        List<Permission> permissions = permissionService.selectList(permission);
        if (permissions != null) {
            return ReturnData.GET_SUCCESS(permissions);
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
    public ReturnData<PageResult> selectPage(Integer start, Integer limit, Permission permission) {
        PageResult pageResult = permissionService.selectPage(start, limit, permission);
        if (pageResult != null) {
            return ReturnData.GET_SUCCESS(pageResult);
        }
        return ReturnData.GET_FAIL();
    }

    /**
     * 得到权限菜单
     * @param permission
     * @return
     */
    @RequestMapping(value = "permissionMenus", method = RequestMethod.POST)
    public HashMap<String,Object> permissionMenus(Permission permission) {
        HashMap<String,Object> res = new HashMap<>();
        //查询不重复的一级菜单
        List<Permission> onelist = permissionService.selectDistinct(permission);
        res.put("data", onelist);
        return res;
    }
    
}


