package com.it.entity;
 

import java.io.Serializable;
import lombok.Data;

@Data
public class Product implements Serializable {
    private static final long serialVersionUID = -91921912591678602L;

    
     
    private Integer id;
    /**
    * 名称
    */ 
    private String name;
    /**
    * 图片文件名
    */ 
    private String filename;
    /**
    * 一级分类ID
    */ 
    private Integer categoryid1;
    /**
    * 二级分类ID
    */ 
    private Integer categoryid2;
    /**
    * 描述
    */ 
    private String content;

    /**
     * 一级分类名称
     */
    private String categoryName1;
    /**
     * 二级分类名称
     */
    private String categoryName2;
    /**
      搜索关键字
    */
    private String key;
    /**
     * 现有库存
     */
    private Integer stockNum;
    /**
     * 价格
     */
    private Double price;
    /**
     * 上下架
     */
    private String issj;
    /**
     * 特价
     */
    private Double tprice;
    /**
     * 销售数量
     */
    private Integer saleNum;

    /**
     * 开始日期
     */
    private String stime;
    /**
     * 结束日期
     */
    private String etime;
    /**
     * 最大价格
     */
    private Double maxPrice;
    /**
     * 最小价格
     */
    private Double minPrice;

    /**
     * 排序
     */
    private String sortBy;
    /**
     * 评分
     */
    private Double average;
    /**
     * 商家ID
     */
    private Integer sellerid;
    /**
     * 商家
     */
    private String sellerName;
    /**
     * 审核
     */
    private String audit;
}
