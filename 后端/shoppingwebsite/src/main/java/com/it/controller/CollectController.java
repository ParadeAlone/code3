package com.it.controller;

import com.it.entity.Collect;
import com.it.service.CollectService;
import org.springframework.web.bind.annotation.*;
import com.it.common.ReturnData;
import com.it.common.page.PageResult;
import java.util.List;

import javax.annotation.Resource;


@RestController
@RequestMapping("/collect")
public class CollectController {
    /**
     * 服务对象
     */
    @Resource
    private CollectService collectService;

    /**
     * 通过主键查询单条数据
     *
     * @param collect 参数对象
     * @return 单条数据
     */
    @RequestMapping(value = "get", method = RequestMethod.POST)
    public ReturnData<Collect> selectOne(Collect collect) {
        Collect result = collectService.selectById(collect.getId());
        if(result != null){
            return ReturnData.GET_SUCCESS(result);
        }
        return ReturnData.GET_FAIL();
    }
    
    /**
     * 新增一条数据
     *
     */
    @RequestMapping(value = "insert", method = RequestMethod.POST)
    public ReturnData<Collect> insert(Collect collect) {
        List<Collect> collectList = collectService.selectList(collect);
        if (collectList.size() == 0) {
            int result = collectService.insert(collect);
            return ReturnData.ADD_SUCCESS();
        }
        return ReturnData.ADD_FAIL();
    }

    /**
     * 修改一条数据
     *
     * @param collect 实体类
     * @return Response对象
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public ReturnData<Collect> update(Collect collect) {
        Collect result = collectService.update(collect);
        if (result != null) {
            return ReturnData.UPDATE_SUCCESS(result);
        }
        return ReturnData.UPDATE_FAIL();
    }

    /**
     * 删除一条数据
     *
     * @param collect 参数对象
     * @return Response对象
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public ReturnData<Collect> delete(Collect collect) {
        int result = collectService.deleteById(collect.getId());
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
    public ReturnData<List<Collect>> selectAll(Collect collect) {
        List<Collect> collects = collectService.selectList(collect);
        if (collects != null) {
            return ReturnData.GET_SUCCESS(collects);
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
    public ReturnData<PageResult> selectPage(Integer start, Integer limit, Collect collect) {
        PageResult pageResult = collectService.selectPage(start, limit, collect);
        if (pageResult != null) {
            return ReturnData.GET_SUCCESS(pageResult);
        }
        return ReturnData.GET_FAIL();
    }
    
}


