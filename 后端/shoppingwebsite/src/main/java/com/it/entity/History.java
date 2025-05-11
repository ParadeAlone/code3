package com.it.entity;
 

import java.io.Serializable;
import lombok.Data;


@Data
public class History implements Serializable {
    private static final long serialVersionUID = 321110621793726096L;

    
 
    private Integer id;
/**
    * 注册用户ID
    */ 
    private Integer memberid;
/**
    * 商品ID
    */ 
    private Integer productid;
/**
    * 权重
    */ 
    private Double score;
 
    /**
      搜索关键字
    */
    private String key;
 
}
