package com.it.entity;
 

import java.io.Serializable;
import java.util.List;

import lombok.Data;


@Data
public class Comment implements Serializable {
    private static final long serialVersionUID = -26030919075204567L;

    
     
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
     * 评分
     */
    private Integer score;
    /**
    * 评价内容
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
     * 评价人
     */
    private String memberName;
    /**
     * 评价头像
     */
    private String filename;
    /**
     * 商品名称
     */
    private String productName;
    /**
     * 回复集合
     */
    private List<Reply> replyList;
    /**
     * 商家ID
     */
    private Integer sellerid;

}
