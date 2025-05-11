package com.it.service.impl;

import com.it.dao.ProductDAO;
import com.it.entity.Inventory;
import com.it.dao.InventoryDAO;
import com.it.entity.Product;
import com.it.service.InventoryService;
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


@Service("inventoryService")
public class InventoryServiceImpl implements InventoryService {
    @Resource
    private InventoryDAO inventoryDAO;
    @Resource
    private ProductDAO productDAO;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Inventory selectById(Integer id) {
        return this.inventoryDAO.selectById(id);
    }

    /**
     * 分页查询
     *
     * @param start 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public PageResult selectPage(int start, int limit, Inventory inventory) {
        PageRequest pageRequest=new PageRequest(start,limit);
        return PageUtils.getPageResult(pageRequest, getPageInfo(pageRequest,inventory));
    }

    /**
     * 调用分页插件完成分页
     * @param pageRequest
     * @return
     */
    private PageInfo<Inventory> getPageInfo(PageRequest pageRequest, Inventory inventory) {
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();
        PageHelper.startPage(pageNum, pageSize);
        List<Inventory> inventorys = this.inventoryDAO.selectList(inventory);
        return new PageInfo<>(inventorys);
    }

    /**
     * 查询所有
     *
     * @return 实例对象的集合
     */
    @Override
    public List<Inventory> selectAll( Inventory inventory) {
        return this.inventoryDAO.selectAll(inventory);
    }
    
    /**
     * 根据条件查询
     *
     * @return 实例对象的集合
     */
    @Override
    public List<Inventory> selectList(Inventory inventory) {
        return this.inventoryDAO.selectList(inventory);
    }
    
    /**
     * 新增数据
     *
     * @param inventory 实例对象
     * @return 实例对象
     */
    @Override
    public int insert(Inventory inventory) {
        Product product = productDAO.selectById(inventory.getProductid());
        inventory.setSavetime(Info.getDateStr().substring(0,10 ));
        inventory.setDelstatus("0");
        return this.inventoryDAO.insert(inventory);
    }

    /**
     * 批量新增
     *
     * @param inventorys 实例对象的集合
     * @return 生效的条数
     */
    @Override
    public int batchInsert(List<Inventory> inventorys) {
        return this.inventoryDAO.batchInsert(inventorys);
    }

    /**
     * 修改数据
     *
     * @param inventory 实例对象
     * @return 实例对象
     */
    @Override
    public Inventory update(Inventory inventory) {
        this.inventoryDAO.update(inventory);
        return this.selectById(inventory.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public int deleteById(Integer id) {
        return this.inventoryDAO.deleteById(id);
    }
    
    /**
     * 查询总数据数
     *
     * @return 数据总数
     */
    @Override
    public int count(){
        return this.inventoryDAO.count();
    }
}


