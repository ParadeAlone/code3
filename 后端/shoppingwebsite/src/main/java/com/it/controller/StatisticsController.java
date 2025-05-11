package com.it.controller;

import com.it.entity.Category;
import com.it.entity.Product;
import com.it.entity.TDetail;
import com.it.entity.TOrder;
import com.it.service.CategoryService;
import com.it.service.ProductService;
import com.it.service.TOrderService;
import com.it.util.Info;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.*;

@RestController
@RequestMapping("/statistics")
public class StatisticsController {

    @Resource
    private CategoryService categoryService;
    @Resource
    private ProductService productService;
    @Resource
    private TOrderService tOrderService;

    /**
     * 折线图
     * @return
     */
    @RequestMapping(value = "tj1", method = RequestMethod.POST)
    public HashMap<String,Object> tj1(TOrder tOrder) {
        HashMap res = new HashMap();
        List nlist = new ArrayList<>();
        List vlist = new ArrayList<>();
        List<TOrder> tOrderList = tOrderService.selectSellerMoney(tOrder);
        for (TOrder tOrder1 : tOrderList) {
            nlist.add(tOrder1.getSavetime());
            vlist.add(tOrder1.getTotal());
        }
        res.put("vlist",vlist);
        res.put("nlist", nlist);
        return res;
    }

    /**
     * 饼图
     * @return
     */
//    @RequestMapping(value = "pieSale", method = RequestMethod.POST)
//    public HashMap<String,Object> pieSale(Category category) {
//        HashMap<String,Object> res = new HashMap<String,Object>();
//        List datelist = new ArrayList<>();
//        List<String> plist = new ArrayList<>();
//        category.setNotCategoryid1("0");
//        List<Category> categories = categoryService.selectList(category);
//        for (Category category1:categories){
//            plist.add(category1.getName());
//
//            HashMap map = new HashMap();
//            map.put("name", category1.getName());
//            map.put("value", category1.getNum());
//            datelist.add(map);
//        }
//        res.put("plist", plist);
//        res.put("data", datelist);
//        return res;
//    }

    /**
     * 柱状图
     * @return
     */
    @RequestMapping(value = "tj2", method = RequestMethod.POST)
    public HashMap<String,Object> tj2(Product product) {
        HashMap<String,Object> res = new HashMap<String,Object>();
        List<String> nlist = new ArrayList<>();
        List<Integer> vlist = new ArrayList<>();
        List<Product> products = productService.selectList(product);
        for (Product product1 : products) {
            nlist.add(product1.getName());
            vlist.add(product1.getSaleNum());
        }
        res.put("nlist", nlist);
        res.put("vlist", vlist);
        return res;
    }
}
