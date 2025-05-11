package com.it.entity;
 

import java.io.Serializable;
import lombok.Data;


@Data
public class Imgadv implements Serializable {
    private static final long serialVersionUID = -85956836934929380L;

    
     
    private Integer id;
    /**
    * 图片文件名
    */ 
    private String filename;
    /**
    * 链接地址
    */ 
    private String url;
 
    /**
      搜索关键字
    */
    private String key;
 
}
