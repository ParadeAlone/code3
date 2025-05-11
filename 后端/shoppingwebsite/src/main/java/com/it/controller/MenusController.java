package com.it.controller;

import com.it.entity.Menus;
import com.it.entity.Menustree;
import com.it.service.MenusService;
import org.springframework.web.bind.annotation.*;
import com.it.common.ReturnData;
import com.it.common.page.PageResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;


@RestController
@RequestMapping("/menus")
public class MenusController {
    /**
     * 服务对象
     */
    @Resource
    private MenusService menusService;

    /**
     * 通过主键查询单条数据
     *
     * @param menus 参数对象
     * @return 单条数据
     */
    @RequestMapping(value = "get", method = RequestMethod.POST)
    public ReturnData<Menus> selectOne(Menus menus) {
        Menus result = menusService.selectById(menus.getId());
        if(result != null){
            return ReturnData.GET_SUCCESS(result);
        }
        return ReturnData.GET_FAIL();
    }
    
    /**
     * 新增一条数据
     *
     * @param menus 实体类
     * @return Response对象
     */
    @RequestMapping(value = "insert", method = RequestMethod.POST)
    public ReturnData<Menus> insert(Menus menus) {
        int result = menusService.insert(menus);
        if (result > 0) {
            return ReturnData.ADD_SUCCESS();
        }
        return ReturnData.ADD_FAIL();
    }

    /**
     * 修改一条数据
     *
     * @param menus 实体类
     * @return Response对象
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public ReturnData<Menus> update(Menus menus) {
        Menus result = menusService.update(menus);
        if (result != null) {
            return ReturnData.UPDATE_SUCCESS(result);
        }
        return ReturnData.UPDATE_FAIL();
    }

    /**
     * 删除一条数据
     *
     * @param menus 参数对象
     * @return Response对象
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public ReturnData<Menus> delete(Menus menus) {
        int result = menusService.deleteById(menus.getId());
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
    public ReturnData<List<Menus>> selectAll(Menus menus) {
        List<Menus> menuss = menusService.selectList(menus);
        if (menuss != null) {
            return ReturnData.GET_SUCCESS(menuss);
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
    public ReturnData<PageResult> selectPage(Integer start, Integer limit, Menus menus) {
        PageResult pageResult = menusService.selectPage(start, limit, menus);
        if (pageResult != null) {
            return ReturnData.GET_SUCCESS(pageResult);
        }
        return ReturnData.GET_FAIL();
    }

    /**
     * 树形菜单数据
     * @return
     */
    @RequestMapping(value = "selectTreeAll", method = RequestMethod.POST)
    public HashMap<String,Object> selectTreeAll() {
        HashMap<String,Object> res  = new HashMap<>();
        List<Menustree> menustrees = new ArrayList<Menustree>();
        Menus menus = new Menus();
        List<Menus> list = menusService.selectAll(menus);
        for(Menus menus1:list){
            List<Menustree> list1 = new ArrayList<Menustree>();
            Menustree menustree = new Menustree();
            menustree.setLabel(menus1.getName());
            menustree.setUrl(menus1.getUrl());
            menustree.setId(menus1.getId());
            menustree.setTreenum(1);



            for(Menus menus2:menus1.getTwolist()){
                List<Menustree> list2 = new ArrayList<Menustree>();
                Menustree menustree1 = new Menustree();
                menustree1.setLabel(menus2.getName());
                menustree1.setUrl(menus2.getUrl());
                menustree1.setId(menus2.getId());
                menustree1.setTreenum(2);


                for(Menus menus3:menus2.getThreelist()){
                    Menustree menustree2 = new Menustree();
                    menustree2.setLabel(menus3.getName());
                    menustree2.setUrl(menus3.getUrl());
                    menustree2.setId(menus3.getId());
                    menustree2.setTreenum(3);
                    list2.add(menustree2);
                }
                menustree1.setChildren(list2);
                list1.add(menustree1);

            }

            menustree.setChildren(list1);
            menustrees.add(menustree);

        }
        res.put("data", menustrees);
        return res;
    }
    
}


