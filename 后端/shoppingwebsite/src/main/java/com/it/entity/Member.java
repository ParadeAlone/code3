package com.it.entity;
 

import java.io.Serializable;
import lombok.Data;


@Data
public class Member implements Serializable {
    private static final long serialVersionUID = -75776630297971836L;

    
     
    private Integer id;
    /**
    * 登录名
    */ 
    private String uname;
    /**
    * 密码
    */ 
    private String upass;
    /**
    * 姓名
    */ 
    private String tname;
    /**
    * 图片文件名
    */ 
    private String filename;
    /**
    * 联系电话
    */ 
    private String tel;
    /**
    * 状态
    */ 
    private String status;
 
    /**
      搜索关键字
    */
    private String key;
    /**
     * 新密码
     */
    private String newsupass;


}
