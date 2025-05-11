package com.it.entity;
 

import java.io.Serializable;
import lombok.Data;


@Data
public class Reply implements Serializable {
    private static final long serialVersionUID = 335199128356936217L;

    
     
    private Integer id;
    /**
    * 注册用户ID
    */ 
    private Integer memberid;
    /**
    * 评价ID
    */ 
    private Integer commentid;
    /**
    * 回复内容
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
    /**
     * 注册用户姓名
     */
    private String memberName;
    /**
     * 注册用户图片
     */
    private String filename;
 
}
