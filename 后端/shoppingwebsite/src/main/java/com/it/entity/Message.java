package com.it.entity;
 

import java.io.Serializable;
import lombok.Data;


@Data
public class Message implements Serializable {
    private static final long serialVersionUID = -73397994391185611L;

    
     
    private Integer id;
    /**
    * 用户ID
    */ 
    private Integer memberid;
    /**
    * 留言内容
    */ 
    private String content;
    /**
    * 时间
    */ 
    private String savetime;
    /**
    * 回复内容
    */ 
    private String hfcontent;
 
    /**
      搜索关键字
    */
    private String key;
    /**
     * 留言人
     */
    private String memberName;
    /**
     * 图片
     */
    private String filename;


}
