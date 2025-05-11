package com.it.entity;
 

import java.io.Serializable;
import lombok.Data;


@Data
public class TDetail implements Serializable {
    private static final long serialVersionUID = -28370235172856930L;

    
     
    private Integer id;
    /**
    * 订单号
    */ 
    private String ddno;
     
    private Integer memberid;
    /**
    * 商品ID
    */ 
    private Integer productid;
    /**
    * 数量
    */ 
    private Integer num;
     
    private String status;
     
    /**
      搜索关键字
    */
    private String key;


    /**
     * 日期
     */
    private String savetime;
    /**
     * 开始日期
     */
    private String stime;
    /**
     * 结束日期
     */
    private String etime;

}
