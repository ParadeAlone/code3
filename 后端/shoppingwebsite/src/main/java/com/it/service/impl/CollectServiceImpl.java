package com.it.service.impl;

import com.it.entity.Collect;
import com.it.dao.CollectDAO;
import com.it.service.CollectService;
import org.springframework.stereotype.Service;
import com.it.common.page.PageResult;
import com.it.common.page.PageRequest;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.it.common.util.PageUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


@Service("collectService")
public class CollectServiceImpl implements CollectService {
    @Resource
    private CollectDAO collectDAO;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Collect selectById(Integer id) {
        return this.collectDAO.selectById(id);
    }

    /**
     * 分页查询
     *
     * @param start 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public PageResult selectPage(int start, int limit, Collect collect) {
        PageRequest pageRequest=new PageRequest(start,limit);
        return PageUtils.getPageResult(pageRequest, getPageInfo(pageRequest,collect));
    }

    /**
     * 调用分页插件完成分页
     * @param pageRequest
     * @return
     */
    private PageInfo<Collect> getPageInfo(PageRequest pageRequest, Collect collect) {
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();
        PageHelper.startPage(pageNum, pageSize);
        List<Collect> collects = this.collectDAO.selectList(collect);
        return new PageInfo<>(collects);
    }

    /**
     * 查询所有
     *
     * @return 实例对象的集合
     */
    @Override
    public List<Collect> selectAll( Collect collect) {
        return this.collectDAO.selectAll(collect);
    }
    
    /**
     * 根据条件查询
     *
     * @return 实例对象的集合
     */
    @Override
    public List<Collect> selectList(Collect collect) {
        return this.collectDAO.selectList(collect);
    }
    
    /**
     * 新增数据
     *
     * @param collect 实例对象
     * @return 实例对象
     */
    @Override
    public int insert(Collect collect) {
        return this.collectDAO.insert(collect);
    }

    /**
     * 批量新增
     *
     * @param collects 实例对象的集合
     * @return 生效的条数
     */
    @Override
    public int batchInsert(List<Collect> collects) {
        return this.collectDAO.batchInsert(collects);
    }

    /**
     * 修改数据
     *
     * @param collect 实例对象
     * @return 实例对象
     */
    @Override
    public Collect update(Collect collect) {
        this.collectDAO.update(collect);
        return this.selectById(collect.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public int deleteById(Integer id) {
        return this.collectDAO.deleteById(id);
    }
    
    /**
     * 查询总数据数
     *
     * @return 数据总数
     */
    @Override
    public int count(){
        return this.collectDAO.count();
    }
}


