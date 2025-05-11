package com.it.service.impl;

import com.it.entity.Menus;
import com.it.dao.MenusDAO;
import com.it.service.MenusService;
import org.springframework.stereotype.Service;
import com.it.common.page.PageResult;
import com.it.common.page.PageRequest;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.it.common.util.PageUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


@Service("menusService")
public class MenusServiceImpl implements MenusService {
    @Resource
    private MenusDAO menusDAO;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Menus selectById(Integer id) {
        return this.menusDAO.selectById(id);
    }

    /**
     * 分页查询
     *
     * @param start 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public PageResult selectPage(int start, int limit, Menus menus) {
        PageRequest pageRequest=new PageRequest(start,limit);
        return PageUtils.getPageResult(pageRequest, getPageInfo(pageRequest,menus));
    }

    /**
     * 调用分页插件完成分页
     * @param pageRequest
     * @return
     */
    private PageInfo<Menus> getPageInfo(PageRequest pageRequest, Menus menus) {
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();
        PageHelper.startPage(pageNum, pageSize);
        List<Menus> menuss = this.menusDAO.selectList(menus);
        return new PageInfo<>(menuss);
    }

    /**
     * 查询所有
     *
     * @return 实例对象的集合
     */
    @Override
    public List<Menus> selectAll( Menus menus) {
        menus.setUpid(0);
        menus.setSortBy("serial");
        List<Menus> list = this.menusDAO.selectList(menus);
        for(Menus menus1:list){
            Menus menus2 = new Menus();
            menus2.setUpid(menus1.getId());
            menus2.setSortBy("serial");
            List<Menus> twolist = menusDAO.selectList(menus2);
            for(Menus menus3:twolist){
                Menus menus4 = new Menus();
                menus4.setUpid(menus3.getId());
                menus4.setSortBy("serial");
                List<Menus> threelist = menusDAO.selectList(menus4);
                menus3.setThreelist(threelist);
            }
            menus1.setTwolist(twolist);
        }
        return list;
    }

    /**
     * 根据条件查询
     *
     * @return 实例对象的集合
     */
    @Override
    public List<Menus> selectList(Menus menus) {
        menus.setUpid(0);
        List<Menus> menuslist1 = this.menusDAO.selectList(menus);
        for (Menus menus1 : menuslist1) {
            menus1.setLabel(menus1.getName());
            Menus menusobj1 = new Menus();
            menusobj1.setUpid(menus1.getId());
            menusobj1.setSortBy(menus.getSortBy());
            List<Menus> menuslist2 = menusDAO.selectList(menusobj1);
            for (Menus menus2 : menuslist2) {
                menus2.setLabel(menus2.getName());

                Menus menusobj2 = new Menus();
                menusobj2.setUpid(menus2.getId());
                menusobj2.setSortBy(menus.getSortBy());
                List<Menus> menuslist3 = menusDAO.selectList(menusobj2);
                for (Menus menus3 : menuslist3) {
                    menus3.setLabel(menus3.getName());
                }
                menus2.setChildren(menuslist3);
            }
            menus1.setChildren(menuslist2);
        }
        return menuslist1;
    }
    
    /**
     * 新增数据
     *
     * @param menus 实例对象
     * @return 实例对象
     */
    @Override
    public int insert(Menus menus) {
        return this.menusDAO.insert(menus);
    }

    /**
     * 批量新增
     *
     * @param menuss 实例对象的集合
     * @return 生效的条数
     */
    @Override
    public int batchInsert(List<Menus> menuss) {
        return this.menusDAO.batchInsert(menuss);
    }

    /**
     * 修改数据
     *
     * @param menus 实例对象
     * @return 实例对象
     */
    @Override
    public Menus update(Menus menus) {
        this.menusDAO.update(menus);
        return this.selectById(menus.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public int deleteById(Integer id) {
        Menus menus = menusDAO.selectById(id);
        if(menus.getUpid()!=0){
            Menus menus1 = new Menus();
            menus1.setUpid(menus.getUpid());
            List<Menus> menuses = menusDAO.selectList(menus1);

            if(menuses.size()==1){
                Menus menus2 = menusDAO.selectById(menus.getUpid());
                menus2.setLeaf(0);
                menusDAO.update(menus2);
                menusDAO.deleteById(id);

                Menus menus3 = new Menus();
                menus3.setUpid(id);
                List<Menus> menuses1 = menusDAO.selectList(menus3);
                for (Menus menus4 : menuses1) {
                    this.menusDAO.deleteById(menus4.getId());
                }

            }else{

                Menus menus3 = new Menus();
                menus3.setUpid(id);
                List<Menus> menuses1 = menusDAO.selectList(menus3);
                for (Menus menus4 : menuses1) {
                    this.menusDAO.deleteById(menus4.getId());
                }
                menusDAO.deleteById(id);


            }
        }else{
            Menus menus1 = new Menus();
            menus1.setUpid(menus.getId());
            List<Menus> menuses = menusDAO.selectList(menus1);
            for (Menus menus2 : menuses) {

                Menus menus3 = new Menus();
                menus3.setUpid(menus2.getId());
                List<Menus> menuses1 = menusDAO.selectList(menus3);
                for (Menus menus4 : menuses1) {
                    this.menusDAO.deleteById(menus4.getId());
                }
                this.menusDAO.deleteById(menus2.getId());
            }
            menusDAO.deleteById(id);
        }
        return 1;
    }
    
    /**
     * 查询总数据数
     *
     * @return 数据总数
     */
    @Override
    public int count(){
        return this.menusDAO.count();
    }
}


