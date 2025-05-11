package com.it.entity;
 

import java.io.Serializable;
import java.util.List;

import lombok.Data;


@Data
public class Category implements Serializable {
    private static final long serialVersionUID = 237436720388718283L;

    
     
    private Integer id;
    /**
    * 一级分类
    */ 
    private String name;
    /**
    * 上一级ID
    */ 
    private Integer fatherid;
 
    /**
      搜索关键字
    */
    private String key;

    /**
     * 二级类别集合
     */
    private List<Category> categories;

    /**
     * 搜索二级类别参数
     */
    private String notCategoryid1;
    /**
     * 数量
     */
    private Integer num;

    /**
     * 开始日期
     */
    private String stime;
    /**
     * 结束日期
     */
    private String etime;
    /**
     * 商家ID
     */
    private Integer sellerid;


 
}
