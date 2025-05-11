package com.it.entity;
 

import java.io.Serializable;
import java.util.List;

import lombok.Data;


@Data
public class Menus implements Serializable {
    private static final long serialVersionUID = -87348286588567604L;

    
     
    private Integer id;
    /**
    * 序号
    */ 
    private Integer serial;
    /**
    * 名称
    */ 
    private String name;
    /**
    * 图标
    */ 
    private String icon;
    /**
    * 访问地址
    */ 
    private String url;
    /**
    * 上一级ID
    */ 
    private Integer upid;
    /**
    * 是否存在下一级菜单
    */ 
    private Integer leaf;
 
    /**
      搜索关键字
    */
    private String key;
    /**
     * 排序
     */
    private String sortBy;


    /**
     菜单集合对象
     */
    private List<Menus> children;

    /**
     * 二级菜单
     */
    private List<Menus> twolist;

    /**
     * 二级菜单
     */
    private List<Menus> threelist;
    /**
     名称
     */
    private String label;

}
