package com.it.controller;

import com.it.entity.History;
import com.it.entity.Member;
import com.it.entity.Notice;
import com.it.entity.Product;
import com.it.service.HistoryService;
import com.it.service.MemberService;
import com.it.service.NoticeService;
import com.it.service.ProductService;
import org.apache.mahout.cf.taste.impl.common.LongPrimitiveIterator;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.EuclideanDistanceSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
import com.it.common.ReturnData;
import com.it.common.page.PageResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@RestController
@RequestMapping("/product")
public class ProductController {
    /**
     * 服务对象
     */
    @Resource
    private ProductService productService;
    @Resource
    private MemberService memberService;
    @Resource
    private NoticeService noticeService;
    @Resource
    private HistoryService historyService;

    /**
     * 通过主键查询单条数据
     *
     * @param product 参数对象
     * @return 单条数据
     */
    @RequestMapping(value = "get", method = RequestMethod.POST)
    public ReturnData<Product> selectOne(Product product) {
        Product result = productService.selectById(product.getId());
        if(result != null){
            return ReturnData.GET_SUCCESS(result);
        }
        return ReturnData.GET_FAIL();
    }

    /**
     * 通过主键查询单条数据
     *
     * @param product 参数对象
     * @return 单条数据
     */
    @RequestMapping(value = "get1", method = RequestMethod.POST)
    public ReturnData<Product> selectOne1(Product product, HttpServletRequest request) {
        Product result = productService.selectById(product.getId());
        Member sessionmember = (Member) request.getSession().getAttribute("sessionmember");
        if(sessionmember!=null){
            History history = new History();
            history.setMemberid(sessionmember.getId());
            history.setProductid(product.getId());
            List<History> historyList = historyService.selectList(history);
            if (historyList.size() == 0) {
                history.setScore(5.0);
                historyService.insert(history);
            }
        }
        if(result != null){
            return ReturnData.GET_SUCCESS(result);
        }
        return ReturnData.GET_FAIL();
    }
    
    /**
     * 新增一条数据
     *
     * @param product 实体类
     * @return Response对象
     */
    @RequestMapping(value = "insert", method = RequestMethod.POST)
    public ReturnData<Product> insert(Product product) {
        int result = productService.insert(product);
        if (result > 0) {
            return ReturnData.ADD_SUCCESS();
        }
        return ReturnData.ADD_FAIL();
    }

    /**
     * 修改一条数据
     *
     * @param product 实体类
     * @return Response对象
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public ReturnData<Product> update(Product product) {
        Product result = productService.update(product);
        if (result != null) {
            return ReturnData.UPDATE_SUCCESS(result);
        }
        return ReturnData.UPDATE_FAIL();
    }

    /**
     * 删除一条数据
     *
     * @param product 参数对象
     * @return Response对象
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public ReturnData<Product> delete(Product product) {
        int result = productService.deleteById(product.getId());
        if (result > 0) {
            return ReturnData.DELETE_SUCCESS();
        }
        return ReturnData.DELETE_FAIL();
    }

    /**
     * 查询全部
     *
     * @return Response对象
     */
    @RequestMapping(value = "selectAll", method = RequestMethod.POST)
    public ReturnData<List<Product>> selectAll(Product product) {
        List<Product> products = productService.selectList(product);
        if (products != null) {
            return ReturnData.GET_SUCCESS(products);
        }
        return ReturnData.GET_FAIL();
    }

    /**
     * 分页查询
     *
     * @param start 偏移
     * @param limit 条数
     * @return Response对象
     */
    @RequestMapping(value = "selectPage", method = RequestMethod.POST)
    public ReturnData<PageResult> selectPage(Integer start, Integer limit, Product product) {
        PageResult pageResult = productService.selectPage(start, limit, product);
        if (pageResult != null) {
            return ReturnData.GET_SUCCESS(pageResult);
        }
        return ReturnData.GET_FAIL();
    }



    @RequestMapping(value = "createDataFile", method = RequestMethod.POST)
    public HashMap<String, Object> createDataFile(HttpServletRequest request, HttpServletResponse response) throws FileNotFoundException {
        HashMap<String, Object> res = new HashMap<String, Object>();
        File outFile = new File("src/data/data.txt");
        PrintWriter pw = new PrintWriter(outFile);
        List<History> histories = historyService.selectAll(null);
        Writer out;
        try {
            //输入流,进行写入操作
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile, true), "utf-8"), 10240);
            for (int i = 0; i < histories.size(); i++) {
                History history = histories.get(i);
                //开始写
                out.write(history.getMemberid()+","+history.getProductid()+","+history.getScore());
                out.write("\r\n");
            }
            //清空缓存区,完全写入
            out.flush();
            //关闭流
            out.close();
        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        return res;
    }


    //协同过滤
    final static int NEIGHBORHOOD_NUM = 5;//临近的用户个数
    final static int RECOMMENDER_NUM = 10;//推荐物品的最大个数
    @RequestMapping("loveitems")
    public HashMap<String, Object> loveitems(long memberid, HttpServletRequest request, HttpServletResponse response) throws FileNotFoundException {
        HashMap<String, Object> res = new HashMap<String, Object>();
        List<Product> pubdataList = new ArrayList<Product>();
        List<Long> ids = new ArrayList<Long>();
        try {
            //权重计算使用数据库中的值，也可以使用文件形式
            String file = "src/data/data.txt";
            DataModel model = new FileDataModel(new File(file));//数据模型
            UserSimilarity user = new EuclideanDistanceSimilarity(model);//用户相识度算法
            NearestNUserNeighborhood neighbor = new NearestNUserNeighborhood(NEIGHBORHOOD_NUM, user, model);
            //用户近邻算法
            Recommender r = new GenericUserBasedRecommender(model, neighbor, user);//用户推荐算法
            LongPrimitiveIterator iter = model.getUserIDs();///得到用户ID

            while (iter.hasNext()) {
                long uid = iter.nextLong();
                if (uid == memberid) {
                    System.out.println("uid=="+uid +"      memberid="+memberid);
                    List<RecommendedItem> list = r.recommend(uid, RECOMMENDER_NUM);
                    for (RecommendedItem ritem : list) {
                        ids.add(ritem.getItemID());
                        System.out.println("推荐：" + ritem.getItemID() + " -- " + ritem.getValue());
                    }
                }
            }

            System.out.println("推荐数据长度：" + ids.size());
            for(int i = 0;i<ids.size();i++){
                Product product = productService.selectById(Integer.valueOf(ids.get(i).toString()));
                pubdataList.add(product);
            }

            res.put("data",pubdataList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

}


