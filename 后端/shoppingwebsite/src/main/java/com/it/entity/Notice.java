package com.it.entity;
 

import java.io.Serializable;
import lombok.Data;


@Data
public class Notice implements Serializable {
    private static final long serialVersionUID = 552696692198586967L;

    
     
    private Integer id;
    /**
    * 注册用户ID
    */ 
    private Integer memberid;
    /**
    * 内容
    */ 
    private String content;
 
    /**
      搜索关键字
    */
    private String key;
 
}
