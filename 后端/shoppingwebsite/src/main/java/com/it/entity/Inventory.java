package com.it.entity;
 

import java.io.Serializable;
import lombok.Data;


@Data
public class Inventory implements Serializable {
    private static final long serialVersionUID = -12763288752318009L;

    
     
    private Integer id;
    /**
    * 商品ID
    */ 
    private Integer productid;
    /**
    * 出入库数量
    */ 
    private Integer num;
    /**
    * 出入库标识
    */ 
    private String flag;
 
    /**
      搜索关键字
    */
    private String key;

    /**
     * 操作日期
     */
    private String savetime;
    /**
     * 删除状态
     */
    private String delstatus;
    /**
     * 商品名称
     */
    private String productName;
    /**
     * 商品图片
     */
    private String filename;
}
