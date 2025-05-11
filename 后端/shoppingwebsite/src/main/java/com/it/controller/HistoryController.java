package com.it.controller;

import com.it.entity.History;
import com.it.service.HistoryService;
import org.springframework.web.bind.annotation.*;
import com.it.common.ReturnData;
import com.it.common.page.PageResult;
import java.util.List;

import javax.annotation.Resource;


@RestController
@RequestMapping("/history")
public class HistoryController {
    /**
     * 服务对象
     */
    @Resource
    private HistoryService historyService;

    /**
     * 通过主键查询单条数据
     *
     * @param history 参数对象
     * @return 单条数据
     */
    @RequestMapping(value = "get", method = RequestMethod.POST)
    public ReturnData<History> selectOne(History history) {
        History result = historyService.selectById(history.getId());
        if(result != null){
            return ReturnData.GET_SUCCESS(result);
        }
        return ReturnData.GET_FAIL();
    }
    
    /**
     * 新增一条数据
     *
     * @param history 实体类
     * @return Response对象
     */
    @RequestMapping(value = "insert", method = RequestMethod.POST)
    public ReturnData<History> insert(History history) {
        int result = historyService.insert(history);
        if (result > 0) {
            return ReturnData.ADD_SUCCESS();
        }
        return ReturnData.ADD_FAIL();
    }

    /**
     * 修改一条数据
     *
     * @param history 实体类
     * @return Response对象
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public ReturnData<History> update(History history) {
        History result = historyService.update(history);
        if (result != null) {
            return ReturnData.UPDATE_SUCCESS(result);
        }
        return ReturnData.UPDATE_FAIL();
    }

    /**
     * 删除一条数据
     *
     * @param history 参数对象
     * @return Response对象
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public ReturnData<History> delete(History history) {
        int result = historyService.deleteById(history.getId());
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
    public ReturnData<List<History>> selectAll(History history) {
        List<History> historys = historyService.selectList(history);
        if (historys != null) {
            return ReturnData.GET_SUCCESS(historys);
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
    public ReturnData<PageResult> selectPage(Integer start, Integer limit, History history) {
        PageResult pageResult = historyService.selectPage(start, limit, history);
        if (pageResult != null) {
            return ReturnData.GET_SUCCESS(pageResult);
        }
        return ReturnData.GET_FAIL();
    }
    
        /**
     * 多项删除
     * @param historyList
     * @return Response对象
     */
    @RequestMapping(value = "deleteByIds", method = RequestMethod.POST)
    public void deleteByIds(@RequestBody List<History> historyList) {
        historyService.deleteByIds(historyList);
    }
}


