package com.it.entity;
 

import java.io.Serializable;
import lombok.Data;


@Data
public class News implements Serializable {
    private static final long serialVersionUID = -77078491420034260L;

    
     
    private Integer id;
    /**
    * 标题
    */ 
    private String title;
    /**
    * 图片文件名
    */ 
    private String filename;
    /**
    * 内容
    */ 
    private String content;
    /**
    * 时间
    */ 
    private String savetime;
 
    /**
      搜索关键字
    */
    private String key;
 
}
