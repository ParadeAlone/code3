package com.it.entity;
 

import java.io.Serializable;
import lombok.Data;


@Data
public class TOrder implements Serializable {
    private static final long serialVersionUID = 688081088104693691L;

    
     
    private Integer id;
    /**
    * 订单编号
    */ 
    private String ddno;
    /**
    * 用户ID
    */ 
    private Integer memberid;
    /**
    * 商品信息
    */ 
    private String productInfo;
    /**
     * 收货地址
     */
    private String address;
    /**
    * 金额
    */ 
    private Double total;
    /**
    * 时间
    */ 
    private String savetime;
    /**
    * 状态
    */ 
    private String status;

    /**
      搜索关键字
    */
    private String key;
    /**
     * 收货地址ID
     */
    private Integer addressid;

    /**
     * 起止时间
     */
    private String stime;

    /**
     * 结束时间
     */
    private String etime;
    /**
     * 商家ID
     */
    private Integer sellerid;
    /**
     * 商家
     */
    private String sellerName;
}
