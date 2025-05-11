package com.it.entity;
 

import java.io.Serializable;
import lombok.Data;

@Data
public class Address implements Serializable {
    private static final long serialVersionUID = 200953114047229629L;

    
     
    private Integer id;
    /**
    * 用户ID
    */ 
    private Integer memberid;
    /**
    * 姓名
    */ 
    private String name;
    /**
    * 电话
    */ 
    private String tel;
    /**
    * 地址
    */ 
    private String addr;
    /**
    * 是否默认
    */ 
    private String ismr;
 
    /**
      搜索关键字
    */
    private String key;
 
}
