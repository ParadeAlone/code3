package com.it.controller;

import com.it.entity.Comment;
import com.it.entity.TDetail;
import com.it.service.CommentService;
import com.it.service.TDetailService;
import org.springframework.web.bind.annotation.*;
import com.it.common.ReturnData;
import com.it.common.page.PageResult;
import java.util.List;

import javax.annotation.Resource;


@RestController
@RequestMapping("/comment")
public class CommentController {
    /**
     * 服务对象
     */
    @Resource
    private CommentService commentService;
    @Resource
    private TDetailService tDetailService;

    /**
     * 通过主键查询单条数据
     *
     * @param comment 参数对象
     * @return 单条数据
     */
    @RequestMapping(value = "get", method = RequestMethod.POST)
    public ReturnData<Comment> selectOne(Comment comment) {
        Comment result = commentService.selectById(comment.getId());
        if(result != null){
            return ReturnData.GET_SUCCESS(result);
        }
        return ReturnData.GET_FAIL();
    }
    
    /**
     * 新增一条数据
     *
     * @param comment 实体类
     * @return Response对象
     */
    @RequestMapping(value = "insert", method = RequestMethod.POST)
    public ReturnData<Comment> insert(Comment comment) {
        TDetail tDetail = new TDetail();
        tDetail.setMemberid(comment.getMemberid());
        tDetail.setProductid(comment.getProductid());
        tDetail.setStatus("交易完成");
        List<TDetail> tDetailList = tDetailService.selectList(tDetail);
        if (tDetailList.size() == 0) {
            return ReturnData.ADD_FAIL();
        }else{
            int result = commentService.insert(comment);
            return ReturnData.ADD_SUCCESS();
        }
    }

    /**
     * 修改一条数据
     *
     * @param comment 实体类
     * @return Response对象
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public ReturnData<Comment> update(Comment comment) {
        Comment result = commentService.update(comment);
        if (result != null) {
            return ReturnData.UPDATE_SUCCESS(result);
        }
        return ReturnData.UPDATE_FAIL();
    }

    /**
     * 删除一条数据
     *
     * @param comment 参数对象
     * @return Response对象
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public ReturnData<Comment> delete(Comment comment) {
        int result = commentService.deleteById(comment.getId());
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
    public ReturnData<List<Comment>> selectAll(Comment comment) {
        List<Comment> comments = commentService.selectList(comment);
        if (comments != null) {
            return ReturnData.GET_SUCCESS(comments);
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
    public ReturnData<PageResult> selectPage(Integer start, Integer limit, Comment comment) {
        PageResult pageResult = commentService.selectPage(start, limit, comment);
        if (pageResult != null) {
            return ReturnData.GET_SUCCESS(pageResult);
        }
        return ReturnData.GET_FAIL();
    }
    
}


