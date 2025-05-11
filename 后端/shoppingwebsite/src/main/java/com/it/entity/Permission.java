package com.it.entity;
 

import java.io.Serializable;
import java.util.List;

import lombok.Data;


@Data
public class Permission implements Serializable {
    private static final long serialVersionUID = 612092046094889605L;

    
     
    private Integer id;
    /**
    * 角色ID
    */ 
    private Integer roleid;
    /**
    * 菜单ID
    */ 
    private Integer menusid;
    /**
    * 二级菜单ID
    */ 
    private Integer upmenusid;
    /**
    * 一级菜单ID
    */ 
    private Integer topmenusid;

    /**
     * 3代表三级菜单，2代表2级菜单
     */
    private Integer flag;

    /**
     * 二级菜单集合
     */
    private List<Permission> permissions;

    /**
     * 菜单对象
     */
    private Menus menus;

    /**
     * url
     */
    private String menusUrl;
}
