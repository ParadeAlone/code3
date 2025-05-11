package com.it.entity;
 

import java.io.Serializable;
import lombok.Data;

@Data
public class Cart implements Serializable {
    private static final long serialVersionUID = 735540182505140824L;

    
     
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
    * 数量
    */ 
    private Integer num;

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
     * 商品价格
     */
    private Double price;
    /**
     * 商品特价
     */
    private Double tprice;
    /**
     * 小计
     */
    private Double subTotal;
    /**
     * 库存
     */
    private Integer stockNum;
    /**
     * 商家ID
     */
    private Integer sellerid;
}
