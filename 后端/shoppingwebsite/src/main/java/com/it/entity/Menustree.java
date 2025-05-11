package com.it.entity;


import lombok.Data;

import java.io.Serializable;
import java.util.List;


@Data
public class Menustree implements Serializable {
    private static final long serialVersionUID = 904206797707493633L;
     
    private Integer id;

    /**
     * 名称
     */
    private String label;

    /**
     * url
     */
    private String url;


    /**
     * 节点数
     */
    private int treenum;

    /**
     * 下一级集合
     */
    private List<Menustree> children;

}
