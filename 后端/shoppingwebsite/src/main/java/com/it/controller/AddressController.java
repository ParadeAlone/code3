package com.it.controller;

import com.it.entity.Address;
import com.it.service.AddressService;
import org.springframework.web.bind.annotation.*;
import com.it.common.ReturnData;
import com.it.common.page.PageResult;
import java.util.List;

import javax.annotation.Resource;

@RestController
@RequestMapping("/address")
public class AddressController {
    /**
     * 服务对象
     */
    @Resource
    private AddressService addressService;

    @RequestMapping(value = "get", method = RequestMethod.POST)
    public ReturnData<Address> selectOne(Address address) {
        Address result = addressService.selectById(address.getId());
        if(result != null){
            return ReturnData.GET_SUCCESS(result);
        }
        return ReturnData.GET_FAIL();
    }
    

    @RequestMapping(value = "insert", method = RequestMethod.POST)
    public ReturnData<Address> insert(Address address) {
        int result = addressService.insert(address);
        if (result > 0) {
            return ReturnData.ADD_SUCCESS();
        }
        return ReturnData.ADD_FAIL();
    }

    /**
     * 修改一条数据
     *
     * @param address 实体类
     * @return Response对象
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public ReturnData<Address> update(Address address) {
        Address address1 = new Address();
        address1.setMemberid(address.getMemberid());
        List<Address> addresses = addressService.selectList(address1);
        for (Address address2 : addresses) {
            address2.setIsmr("否");
            addressService.update(address2);
        }
        Address result = addressService.update(address);
        if (result != null) {
            return ReturnData.UPDATE_SUCCESS(result);
        }
        return ReturnData.UPDATE_FAIL();
    }

    /**
     * 删除一条数据
     *
     * @param address 参数对象
     * @return Response对象
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public ReturnData<Address> delete(Address address) {
        int result = addressService.deleteById(address.getId());
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
    public ReturnData<List<Address>> selectAll(Address address) {
        List<Address> addresss = addressService.selectList(address);
        if (addresss != null) {
            return ReturnData.GET_SUCCESS(addresss);
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
    public ReturnData<PageResult> selectPage(Integer start, Integer limit, Address address) {
        PageResult pageResult = addressService.selectPage(start, limit, address);
        if (pageResult != null) {
            return ReturnData.GET_SUCCESS(pageResult);
        }
        return ReturnData.GET_FAIL();
    }
    
        /**
     * 多项删除
     * @param addressList
     * @return Response对象
     */
    @RequestMapping(value = "deleteByIds", method = RequestMethod.POST)
    public void deleteByIds(@RequestBody List<Address> addressList) {
        addressService.deleteByIds(addressList);
    }
}


