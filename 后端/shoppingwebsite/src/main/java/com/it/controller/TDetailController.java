package com.it.controller;

import com.it.entity.TDetail;
import com.it.service.TDetailService;
import org.springframework.web.bind.annotation.*;
import com.it.common.ReturnData;
import com.it.common.page.PageResult;
import java.util.List;

import javax.annotation.Resource;


@RestController
@RequestMapping("/tDetail")
public class TDetailController {
    /**
     * 服务对象
     */
    @Resource
    private TDetailService tDetailService;

    /**
     * 通过主键查询单条数据
     *
     * @param tDetail 参数对象
     * @return 单条数据
     */
    @RequestMapping(value = "get", method = RequestMethod.POST)
    public ReturnData<TDetail> selectOne(TDetail tDetail) {
        TDetail result = tDetailService.selectById(tDetail.getId());
        if(result != null){
            return ReturnData.GET_SUCCESS(result);
        }
        return ReturnData.GET_FAIL();
    }
    
    /**
     * 新增一条数据
     *
     * @param tDetail 实体类
     * @return Response对象
     */
    @RequestMapping(value = "insert", method = RequestMethod.POST)
    public ReturnData<TDetail> insert(TDetail tDetail) {
        int result = tDetailService.insert(tDetail);
        if (result > 0) {
            return ReturnData.ADD_SUCCESS();
        }
        return ReturnData.ADD_FAIL();
    }

    /**
     * 修改一条数据
     *
     * @param tDetail 实体类
     * @return Response对象
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public ReturnData<TDetail> update(TDetail tDetail) {
        TDetail result = tDetailService.update(tDetail);
        if (result != null) {
            return ReturnData.UPDATE_SUCCESS(result);
        }
        return ReturnData.UPDATE_FAIL();
    }

    /**
     * 删除一条数据
     *
     * @param tDetail 参数对象
     * @return Response对象
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public ReturnData<TDetail> delete(TDetail tDetail) {
        int result = tDetailService.deleteById(tDetail.getId());
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
    public ReturnData<List<TDetail>> selectAll(TDetail tDetail) {
        List<TDetail> tDetails = tDetailService.selectList(tDetail);
        if (tDetails != null) {
            return ReturnData.GET_SUCCESS(tDetails);
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
    public ReturnData<PageResult> selectPage(Integer start, Integer limit, TDetail tDetail) {
        PageResult pageResult = tDetailService.selectPage(start, limit, tDetail);
        if (pageResult != null) {
            return ReturnData.GET_SUCCESS(pageResult);
        }
        return ReturnData.GET_FAIL();
    }
    
}


