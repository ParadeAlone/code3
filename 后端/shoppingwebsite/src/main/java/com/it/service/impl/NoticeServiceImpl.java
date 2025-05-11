package com.it.service.impl;

import com.it.entity.Notice;
import com.it.dao.NoticeDAO;
import com.it.service.NoticeService;
import org.springframework.stereotype.Service;
import com.it.common.page.PageResult;
import com.it.common.page.PageRequest;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.it.common.util.PageUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


@Service("noticeService")
public class NoticeServiceImpl implements NoticeService {
    @Resource
    private NoticeDAO noticeDAO;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Notice selectById(Integer id) {
        return this.noticeDAO.selectById(id);
    }

    /**
     * 分页查询
     *
     * @param start 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public PageResult selectPage(int start, int limit, Notice notice) {
        PageRequest pageRequest=new PageRequest(start,limit);
        return PageUtils.getPageResult(pageRequest, getPageInfo(pageRequest,notice));
    }

    /**
     * 调用分页插件完成分页
     * @param pageRequest
     * @return
     */
    private PageInfo<Notice> getPageInfo(PageRequest pageRequest, Notice notice) {
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();
        PageHelper.startPage(pageNum, pageSize);
        List<Notice> notices = this.noticeDAO.selectList(notice);
        return new PageInfo<>(notices);
    }

    /**
     * 查询所有
     *
     * @return 实例对象的集合
     */
    @Override
    public List<Notice> selectAll( Notice notice) {
        return this.noticeDAO.selectAll(notice);
    }
    
    /**
     * 根据条件查询
     *
     * @return 实例对象的集合
     */
    @Override
    public List<Notice> selectList(Notice notice) {
        return this.noticeDAO.selectList(notice);
    }
    
    /**
     * 新增数据
     *
     * @param notice 实例对象
     * @return 实例对象
     */
    @Override
    public int insert(Notice notice) {
        return this.noticeDAO.insert(notice);
    }

    /**
     * 批量新增
     *
     * @param notices 实例对象的集合
     * @return 生效的条数
     */
    @Override
    public int batchInsert(List<Notice> notices) {
        return this.noticeDAO.batchInsert(notices);
    }

    /**
     * 修改数据
     *
     * @param notice 实例对象
     * @return 实例对象
     */
    @Override
    public Notice update(Notice notice) {
        this.noticeDAO.update(notice);
        return this.selectById(notice.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public int deleteById(Integer id) {
        return this.noticeDAO.deleteById(id);
    }
    
    /**
     * 查询总数据数
     *
     * @return 数据总数
     */
    @Override
    public int count(){
        return this.noticeDAO.count();
    }
}


