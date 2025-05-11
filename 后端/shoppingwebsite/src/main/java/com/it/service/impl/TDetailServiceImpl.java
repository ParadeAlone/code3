package com.it.service.impl;

import com.it.entity.TDetail;
import com.it.dao.TDetailDAO;
import com.it.service.TDetailService;
import org.springframework.stereotype.Service;
import com.it.common.page.PageResult;
import com.it.common.page.PageRequest;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.it.common.util.PageUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


@Service("tDetailService")
public class TDetailServiceImpl implements TDetailService {
    @Resource
    private TDetailDAO tDetailDAO;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public TDetail selectById(Integer id) {
        return this.tDetailDAO.selectById(id);
    }

    /**
     * 分页查询
     *
     * @param start 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public PageResult selectPage(int start, int limit, TDetail tDetail) {
        PageRequest pageRequest=new PageRequest(start,limit);
        return PageUtils.getPageResult(pageRequest, getPageInfo(pageRequest,tDetail));
    }

    /**
     * 调用分页插件完成分页
     * @param pageRequest
     * @return
     */
    private PageInfo<TDetail> getPageInfo(PageRequest pageRequest, TDetail tDetail) {
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();
        PageHelper.startPage(pageNum, pageSize);
        List<TDetail> tDetails = this.tDetailDAO.selectList(tDetail);
        return new PageInfo<>(tDetails);
    }

    /**
     * 查询所有
     *
     * @return 实例对象的集合
     */
    @Override
    public List<TDetail> selectAll( TDetail tDetail) {
        return this.tDetailDAO.selectAll(tDetail);
    }
    
    /**
     * 根据条件查询
     *
     * @return 实例对象的集合
     */
    @Override
    public List<TDetail> selectList(TDetail tDetail) {
        return this.tDetailDAO.selectList(tDetail);
    }
    
    /**
     * 新增数据
     *
     * @param tDetail 实例对象
     * @return 实例对象
     */
    @Override
    public int insert(TDetail tDetail) {
        return this.tDetailDAO.insert(tDetail);
    }

    /**
     * 批量新增
     *
     * @param tDetails 实例对象的集合
     * @return 生效的条数
     */
    @Override
    public int batchInsert(List<TDetail> tDetails) {
        return this.tDetailDAO.batchInsert(tDetails);
    }

    /**
     * 修改数据
     *
     * @param tDetail 实例对象
     * @return 实例对象
     */
    @Override
    public TDetail update(TDetail tDetail) {
        this.tDetailDAO.update(tDetail);
        return this.selectById(tDetail.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public int deleteById(Integer id) {
        return this.tDetailDAO.deleteById(id);
    }
    
    /**
     * 查询总数据数
     *
     * @return 数据总数
     */
    @Override
    public int count(){
        return this.tDetailDAO.count();
    }
}


