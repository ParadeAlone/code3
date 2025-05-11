package com.it.service.impl;

import com.it.entity.History;
import com.it.dao.HistoryDAO;
import com.it.service.HistoryService;
import org.springframework.stereotype.Service;
import com.it.common.page.PageResult;
import com.it.common.page.PageRequest;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.it.common.util.PageUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


@Service("historyService")
public class HistoryServiceImpl implements HistoryService {
    @Resource
    private HistoryDAO historyDAO;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public History selectById(Integer id) {
        return this.historyDAO.selectById(id);
    }

    /**
     * 分页查询
     *
     * @param start 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public PageResult selectPage(int start, int limit, History history) {
        PageRequest pageRequest=new PageRequest(start,limit);
        return PageUtils.getPageResult(pageRequest, getPageInfo(pageRequest,history));
    }

    /**
     * 调用分页插件完成分页
     * @param pageRequest
     * @return
     */
    private PageInfo<History> getPageInfo(PageRequest pageRequest, History history) {
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();
        PageHelper.startPage(pageNum, pageSize);
        List<History> historys = this.historyDAO.selectList(history);
        return new PageInfo<>(historys);
    }

    /**
     * 查询所有
     *
     * @return 实例对象的集合
     */
    @Override
    public List<History> selectAll( History history) {
        return this.historyDAO.selectAll(history);
    }
    
    /**
     * 根据条件查询
     *
     * @return 实例对象的集合
     */
    @Override
    public List<History> selectList(History history) {
        return this.historyDAO.selectList(history);
    }
    
    /**
     * 新增数据
     *
     * @param history 实例对象
     * @return 实例对象
     */
    @Override
    public int insert(History history) {
        return this.historyDAO.insert(history);
    }

    /**
     * 批量新增
     *
     * @param historys 实例对象的集合
     * @return 生效的条数
     */
    @Override
    public int batchInsert(List<History> historys) {
        return this.historyDAO.batchInsert(historys);
    }

    /**
     * 修改数据
     *
     * @param history 实例对象
     * @return 实例对象
     */
    @Override
    public History update(History history) {
        this.historyDAO.update(history);
        return this.selectById(history.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public int deleteById(Integer id) {
        return this.historyDAO.deleteById(id);
    }
    
    /**
     * 查询总数据数
     *
     * @return 数据总数
     */
    @Override
    public int count(){
        return this.historyDAO.count();
    }
    
    /**
    * 批量删除
    * @param historyList 实例对象的集合
    */
    @Override
    public void deleteByIds(List<History> historyList) {
         this.historyDAO.deleteByIds(historyList);
    }
}


