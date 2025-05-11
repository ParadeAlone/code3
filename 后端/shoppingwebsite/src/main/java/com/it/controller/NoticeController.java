package com.it.controller;

import com.it.entity.Notice;
import com.it.service.NoticeService;
import org.springframework.web.bind.annotation.*;
import com.it.common.ReturnData;
import com.it.common.page.PageResult;
import java.util.List;

import javax.annotation.Resource;


@RestController
@RequestMapping("/notice")
public class NoticeController {
    /**
     * 服务对象
     */
    @Resource
    private NoticeService noticeService;

    /**
     * 通过主键查询单条数据
     *
     * @param notice 参数对象
     * @return 单条数据
     */
    @RequestMapping(value = "get", method = RequestMethod.POST)
    public ReturnData<Notice> selectOne(Notice notice) {
        Notice result = noticeService.selectById(notice.getId());
        if(result != null){
            return ReturnData.GET_SUCCESS(result);
        }
        return ReturnData.GET_FAIL();
    }
    
    /**
     * 新增一条数据
     *
     * @param notice 实体类
     * @return Response对象
     */
    @RequestMapping(value = "insert", method = RequestMethod.POST)
    public ReturnData<Notice> insert(Notice notice) {
        int result = noticeService.insert(notice);
        if (result > 0) {
            return ReturnData.ADD_SUCCESS();
        }
        return ReturnData.ADD_FAIL();
    }

    /**
     * 修改一条数据
     *
     * @param notice 实体类
     * @return Response对象
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public ReturnData<Notice> update(Notice notice) {
        Notice result = noticeService.update(notice);
        if (result != null) {
            return ReturnData.UPDATE_SUCCESS(result);
        }
        return ReturnData.UPDATE_FAIL();
    }

    /**
     * 删除一条数据
     *
     * @param notice 参数对象
     * @return Response对象
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public ReturnData<Notice> delete(Notice notice) {
        int result = noticeService.deleteById(notice.getId());
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
    public ReturnData<List<Notice>> selectAll(Notice notice) {
        List<Notice> notices = noticeService.selectList(notice);
        if (notices != null) {
            return ReturnData.GET_SUCCESS(notices);
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
    public ReturnData<PageResult> selectPage(Integer start, Integer limit, Notice notice) {
        PageResult pageResult = noticeService.selectPage(start, limit, notice);
        if (pageResult != null) {
            return ReturnData.GET_SUCCESS(pageResult);
        }
        return ReturnData.GET_FAIL();
    }
    
}


