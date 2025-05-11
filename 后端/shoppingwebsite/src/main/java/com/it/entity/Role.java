package com.it.entity;
 

import java.io.Serializable;
import lombok.Data;


@Data
public class Role implements Serializable {
    private static final long serialVersionUID = 912515724226490798L;


    /**
     * ID
     */

    private Integer id;

    /**
     * 角色名称
     */
    private String name;
 
    /**
      搜索关键字
    */
    private String key;
    /**
     * 排出ID
     */
    private String notRoleId;
 
}
