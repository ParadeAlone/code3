package com.it.service.impl;

import com.it.dao.ReplyDAO;
import com.it.entity.Comment;
import com.it.dao.CommentDAO;
import com.it.entity.Reply;
import com.it.service.CommentService;
import com.it.util.Info;
import org.springframework.stereotype.Service;
import com.it.common.page.PageResult;
import com.it.common.page.PageRequest;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.it.common.util.PageUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


@Service("commentService")
public class CommentServiceImpl implements CommentService {
    @Resource
    private CommentDAO commentDAO;
    @Resource
    private ReplyDAO replyDAO;


    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Comment selectById(Integer id) {
        return this.commentDAO.selectById(id);
    }

    /**
     * 分页查询
     *
     * @param start 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public PageResult selectPage(int start, int limit, Comment comment) {
        PageRequest pageRequest=new PageRequest(start,limit);
        return PageUtils.getPageResult(pageRequest, getPageInfo(pageRequest,comment));
    }

    /**
     * 调用分页插件完成分页
     * @param pageRequest
     * @return
     */
    private PageInfo<Comment> getPageInfo(PageRequest pageRequest, Comment comment) {
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();
        PageHelper.startPage(pageNum, pageSize);
        List<Comment> comments = this.commentDAO.selectList(comment);
        return new PageInfo<>(comments);
    }

    /**
     * 查询所有
     *
     * @return 实例对象的集合
     */
    @Override
    public List<Comment> selectAll( Comment comment) {
        return this.commentDAO.selectAll(comment);
    }
    
    /**
     * 根据条件查询
     *
     * @return 实例对象的集合
     */
    @Override
    public List<Comment> selectList(Comment comment) {
        List<Comment> commentList =  this.commentDAO.selectList(comment);
        for (Comment comment1 : commentList) {
            Reply reply = new Reply();
            reply.setCommentid(comment1.getId());
            List<Reply> replyList = replyDAO.selectList(reply);
            comment1.setReplyList(replyList);
        }
        return commentList;
    }
    
    /**
     * 新增数据
     *
     * @param comment 实例对象
     * @return 实例对象
     */
    @Override
    public int insert(Comment comment) {
        comment.setSavetime(Info.getDateStr());
        return this.commentDAO.insert(comment);
    }

    /**
     * 批量新增
     *
     * @param comments 实例对象的集合
     * @return 生效的条数
     */
    @Override
    public int batchInsert(List<Comment> comments) {
        return this.commentDAO.batchInsert(comments);
    }

    /**
     * 修改数据
     *
     * @param comment 实例对象
     * @return 实例对象
     */
    @Override
    public Comment update(Comment comment) {
        this.commentDAO.update(comment);
        return this.selectById(comment.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public int deleteById(Integer id) {
        return this.commentDAO.deleteById(id);
    }
    
    /**
     * 查询总数据数
     *
     * @return 数据总数
     */
    @Override
    public int count(){
        return this.commentDAO.count();
    }
}


