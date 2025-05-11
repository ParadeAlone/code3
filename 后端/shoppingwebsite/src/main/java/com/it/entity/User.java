package com.it.entity;
 

import java.io.Serializable;
import lombok.Data;

@Data
public class User implements Serializable {
    private static final long serialVersionUID = 184623455451296265L;

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
    * 电话
    */
    private String tel;


    /**
     * 角色ID
     */
    private Integer roleid;

    /**
     * 状态
     */
    private String status;

    /**
     * 搜索关键字
     */
    private String key;
    /**
     * 排出ID
     */
    private String notroleid;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 审核状态
     */
    private String audit;



}
