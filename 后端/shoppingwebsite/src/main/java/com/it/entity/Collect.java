package com.it.entity;
 

import java.io.Serializable;
import lombok.Data;


@Data
public class Collect implements Serializable {
    private static final long serialVersionUID = 507758130096159998L;

    
     
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
      搜索关键字
    */
    private String key;
    /**
     * 商品名称
     */
    private String productName;
    /**
     * 商品图片
     */
    private String filename;
    /**
     * 价格
     */
    private Double price;
    /**
     * 价格
     */
    private Double tprice;
    /**
     * 库存
     */
    private Integer stockNum;


}
