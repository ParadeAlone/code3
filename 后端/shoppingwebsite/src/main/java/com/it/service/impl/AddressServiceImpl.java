package com.it.service.impl;

import com.it.entity.Address;
import com.it.dao.AddressDAO;
import com.it.service.AddressService;
import org.springframework.stereotype.Service;
import com.it.common.page.PageResult;
import com.it.common.page.PageRequest;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.it.common.util.PageUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


@Service("addressService")
public class AddressServiceImpl implements AddressService {
    @Resource
    private AddressDAO addressDAO;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Address selectById(Integer id) {
        return this.addressDAO.selectById(id);
    }

    /**
     * 分页查询
     *
     * @param start 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public PageResult selectPage(int start, int limit, Address address) {
        PageRequest pageRequest=new PageRequest(start,limit);
        return PageUtils.getPageResult(pageRequest, getPageInfo(pageRequest,address));
    }

    /**
     * 调用分页插件完成分页
     * @param pageRequest
     * @return
     */
    private PageInfo<Address> getPageInfo(PageRequest pageRequest, Address address) {
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();
        PageHelper.startPage(pageNum, pageSize);
        List<Address> addresss = this.addressDAO.selectList(address);
        return new PageInfo<>(addresss);
    }

    /**
     * 查询所有
     *
     * @return 实例对象的集合
     */
    @Override
    public List<Address> selectAll( Address address) {
        return this.addressDAO.selectAll(address);
    }
    
    /**
     * 根据条件查询
     *
     * @return 实例对象的集合
     */
    @Override
    public List<Address> selectList(Address address) {
        return this.addressDAO.selectList(address);
    }
    
    /**
     * 新增数据
     *
     * @param address 实例对象
     * @return 实例对象
     */
    @Override
    public int insert(Address address) {
        return this.addressDAO.insert(address);
    }

    /**
     * 批量新增
     *
     * @param addresss 实例对象的集合
     * @return 生效的条数
     */
    @Override
    public int batchInsert(List<Address> addresss) {
        return this.addressDAO.batchInsert(addresss);
    }

    /**
     * 修改数据
     *
     * @param address 实例对象
     * @return 实例对象
     */
    @Override
    public Address update(Address address) {
        this.addressDAO.update(address);
        return this.selectById(address.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public int deleteById(Integer id) {
        return this.addressDAO.deleteById(id);
    }
    
    /**
     * 查询总数据数
     *
     * @return 数据总数
     */
    @Override
    public int count(){
        return this.addressDAO.count();
    }
    
    /**
    * 批量删除
    * @param addressList 实例对象的集合
    */
    @Override
    public void deleteByIds(List<Address> addressList) {
         this.addressDAO.deleteByIds(addressList);
    }
}


