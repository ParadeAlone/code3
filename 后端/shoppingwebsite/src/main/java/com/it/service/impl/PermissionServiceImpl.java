package com.it.service.impl;

import com.it.dao.MenusDAO;
import com.it.entity.Menus;
import com.it.entity.Permission;
import com.it.dao.PermissionDAO;
import com.it.service.PermissionService;
import org.springframework.stereotype.Service;
import com.it.common.page.PageResult;
import com.it.common.page.PageRequest;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.it.common.util.PageUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


@Service("permissionService")
public class PermissionServiceImpl implements PermissionService {
    @Resource
    private PermissionDAO permissionDAO;
    @Resource
    private MenusDAO menusDAO;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Permission selectById(Integer id) {
        return this.permissionDAO.selectById(id);
    }

    /**
     * 分页查询
     *
     * @param start 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public PageResult selectPage(int start, int limit, Permission permission) {
        PageRequest pageRequest=new PageRequest(start,limit);
        return PageUtils.getPageResult(pageRequest, getPageInfo(pageRequest,permission));
    }

    /**
     * 调用分页插件完成分页
     * @param pageRequest
     * @return
     */
    private PageInfo<Permission> getPageInfo(PageRequest pageRequest, Permission permission) {
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();
        PageHelper.startPage(pageNum, pageSize);
        List<Permission> permissions = this.permissionDAO.selectList(permission);
        return new PageInfo<>(permissions);
    }

    /**
     * 查询所有
     *
     * @return 实例对象的集合
     */
    @Override
    public List<Permission> selectAll( Permission permission) {
        return this.permissionDAO.selectAll(permission);
    }
    
    /**
     * 根据条件查询
     *
     * @return 实例对象的集合
     */
    @Override
    public List<Permission> selectList(Permission permission) {
        return this.permissionDAO.selectList(permission);
    }


    /**
     * 根据条件查询
     *
     * @return 实例对象的集合
     */
    @Override
    public List<Permission> selectDistinct(Permission permission) {
        List<Permission> list = this.permissionDAO.selectDistinct(permission);

        for(Permission permission1:list){
            Menus menus = menusDAO.selectById(permission1.getTopmenusid());
            permission1.setMenus(menus);


            Permission permission2 = new Permission();
            permission2.setTopmenusid(permission1.getTopmenusid());
            permission2.setRoleid(permission.getRoleid());
            List<Permission> permissions = this.permissionDAO.selectDistinct1(permission2);
            for(Permission permission3:permissions){
                Menus menus1 = menusDAO.selectById(permission3.getUpmenusid());
                permission3.setMenus(menus1);
            }

            permission1.setPermissions(permissions);
        }

        return list;
    }
    
    /**
     * 新增数据
     *
     * @param permission 实例对象
     * @return 实例对象
     */
    @Override
    public int insert(Permission permission,String arr,String arr1) {


        //先删除之前授权信息
        List<Permission> permissions = permissionDAO.selectList(permission);
        for(Permission permission1:permissions){
            permissionDAO.deleteById(permission1.getId());
        }
        if(arr!=""){
            String[] arrstr = arr.split(",");
            for(int i=0;i<arrstr.length;i++){
                Menus threemenus = menusDAO.selectById(Integer.parseInt(arrstr[i]));
                Menus twomenus = menusDAO.selectById(threemenus.getUpid());
                Menus onemenus = menusDAO.selectById(twomenus.getUpid());


                permission.setUpmenusid(twomenus.getId());
                permission.setTopmenusid(onemenus.getId());
                permission.setMenusid(Integer.parseInt(arrstr[i]));
                permission.setFlag(3);
                this.permissionDAO.insert(permission);
            }
        }
//        if(arr1!=""){
//            String[] arrstr1 = arr1.split(",");
//            for(int i=0;i<arrstr1.length;i++){
//                Menus menus = menusDAO.selectById(Integer.parseInt(arrstr1[i]));
//                Menus menus1 = menusDAO.selectById(menus.getUpid());
//
//
//                permission.setMenusid(Integer.parseInt(arrstr1[i]));
//                permission.setUpmenusid(menus1.getId());
//                permission.setFlag(2);
//                this.permissionDAO.insert(permission);
//
//
//                Permission permission1 = new Permission();
//                permission1.setMenusid(menus1.getId());
//                permission1.setFlag(1);
//                permission1.setRoleid(permission.getRoleid());
//                this.permissionDAO.insert(permission1);
//
//
//            }
//        }
        return 1;
    }

    /**
     * 批量新增
     *
     * @param permissions 实例对象的集合
     * @return 生效的条数
     */
    @Override
    public int batchInsert(List<Permission> permissions) {
        return this.permissionDAO.batchInsert(permissions);
    }

    /**
     * 修改数据
     *
     * @param permission 实例对象
     * @return 实例对象
     */
    @Override
    public Permission update(Permission permission) {
        this.permissionDAO.update(permission);
        return this.selectById(permission.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public int deleteById(Integer id) {
        return this.permissionDAO.deleteById(id);
    }
    
    /**
     * 查询总数据数
     *
     * @return 数据总数
     */
    @Override
    public int count(){
        return this.permissionDAO.count();
    }
}


