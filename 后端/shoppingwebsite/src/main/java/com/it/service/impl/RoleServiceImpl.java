package com.it.service.impl;

import com.it.entity.Role;
import com.it.dao.RoleDAO;
import com.it.service.RoleService;
import org.springframework.stereotype.Service;
import com.it.common.page.PageResult;
import com.it.common.page.PageRequest;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.it.common.util.PageUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


@Service("roleService")
public class RoleServiceImpl implements RoleService {
    @Resource
    private RoleDAO roleDAO;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Role selectById(Integer id) {
        return this.roleDAO.selectById(id);
    }

    /**
     * 分页查询
     *
     * @param start 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public PageResult selectPage(int start, int limit, Role role) {
        PageRequest pageRequest=new PageRequest(start,limit);
        return PageUtils.getPageResult(pageRequest, getPageInfo(pageRequest,role));
    }

    /**
     * 调用分页插件完成分页
     * @param pageRequest
     * @return
     */
    private PageInfo<Role> getPageInfo(PageRequest pageRequest, Role role) {
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();
        PageHelper.startPage(pageNum, pageSize);
        List<Role> roles = this.roleDAO.selectList(role);
        return new PageInfo<>(roles);
    }

    /**
     * 查询所有
     *
     * @return 实例对象的集合
     */
    @Override
    public List<Role> selectAll( Role role) {
        return this.roleDAO.selectAll(role);
    }
    
    /**
     * 根据条件查询
     *
     * @return 实例对象的集合
     */
    @Override
    public List<Role> selectList(Role role) {
        return this.roleDAO.selectList(role);
    }
    
    /**
     * 新增数据
     *
     * @param role 实例对象
     * @return 实例对象
     */
    @Override
    public int insert(Role role) {
        return this.roleDAO.insert(role);
    }

    /**
     * 批量新增
     *
     * @param roles 实例对象的集合
     * @return 生效的条数
     */
    @Override
    public int batchInsert(List<Role> roles) {
        return this.roleDAO.batchInsert(roles);
    }

    /**
     * 修改数据
     *
     * @param role 实例对象
     * @return 实例对象
     */
    @Override
    public Role update(Role role) {
        this.roleDAO.update(role);
        return this.selectById(role.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public int deleteById(Integer id) {
        return this.roleDAO.deleteById(id);
    }
    
    /**
     * 查询总数据数
     *
     * @return 数据总数
     */
    @Override
    public int count(){
        return this.roleDAO.count();
    }
}


