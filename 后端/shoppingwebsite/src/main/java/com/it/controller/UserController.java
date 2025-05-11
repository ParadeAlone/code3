package com.it.controller;

import com.it.entity.Loginip;
import com.it.entity.Menus;
import com.it.entity.Permission;
import com.it.entity.User;
import com.it.service.LoginipService;
import com.it.service.PermissionService;
import com.it.service.UserService;
import com.it.util.Info;
import org.springframework.web.bind.annotation.*;
import com.it.common.ReturnData;
import com.it.common.page.PageResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/user")
public class UserController {
    /**
     * 服务对象
     */
    @Resource
    private UserService userService;
    @Resource
    private UtilController utilController;
    @Resource
    private LoginipService loginipService;
    @Resource
    private PermissionService permissionService;

    /**
     * 通过主键查询单条数据
     *
     * @param user 参数对象
     * @return 单条数据
     */
    @RequestMapping(value = "get", method = RequestMethod.POST)
    public ReturnData<User> selectOne(User user) {
        User result = userService.selectById(user.getId());
        if (result != null) {
            return ReturnData.GET_SUCCESS(result);
        }
        return ReturnData.GET_FAIL();
    }

    /**
     * 新增一条数据
     *
     * @param user 实体类
     * @return Response对象
     */
    @RequestMapping(value = "insert", method = RequestMethod.POST)
    public ReturnData<User> insert(User user) {
        int result = userService.insert(user);
        if (result > 0) {
            return ReturnData.ADD_SUCCESS();
        }
        return ReturnData.ADD_FAIL();
    }

    /**
     * 修改一条数据
     *
     * @param user 实体类
     * @return Response对象
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public ReturnData<User> update(User user) {
        User result = userService.update(user);
        if (result != null) {
            return ReturnData.UPDATE_SUCCESS(result);
        }
        return ReturnData.UPDATE_FAIL();
    }

    /**
     * 删除一条数据
     *
     * @param user 参数对象
     * @return Response对象
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public ReturnData<User> delete(User user) {
        int result = userService.deleteById(user.getId());
        if (result > 0) {
            return ReturnData.DELETE_SUCCESS();
        }
        return ReturnData.DELETE_FAIL();
    }


    /**
     * 删除一条数据学生
     *
     * @param user 参数对象
     * @return Response对象
     */
    @RequestMapping(value = "deleteStudent", method = RequestMethod.POST)
    public ReturnData<User> deleteStudent(User user) {
        int result = userService.deleteById(user.getId());
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
    public ReturnData<List<User>> selectAll(User user) {
        List<User> users = userService.selectList(user);
        if (users.size() != 0) {
            return ReturnData.GET_SUCCESS(users);
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
    public ReturnData<PageResult> selectPage(Integer start, Integer limit, User user) {
        PageResult pageResult = userService.selectPage(start, limit, user);
        if (pageResult != null) {
            return ReturnData.GET_SUCCESS(pageResult);
        }
        return ReturnData.GET_FAIL();
    }


    /**
     * 登录
     *
     * @param user
     * @param request
     * @return
     */
    /**
     * 登录
     *
     * @param user
     * @param request
     * @return
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public HashMap<String, Object> login(User user, HttpServletRequest request) {
        HashMap<String, Object> res = new HashMap<>();

        User user2 = new User();
        user2.setUname(user.getUname());
        user2.setUpass(user.getUpass());
        List<User> users1 = userService.selectList(user2);
        if (users1.size() == 0) {
            res.put("data", 400);
        } else {
            User user3 = users1.get(0);
            if(user3.getAudit().equals("已通过")){
                if (user3.getStatus().equals("正常")) {
                    User user1 = new User();
                    user1.setUname(user.getUname());
                    List<User> users = userService.selectList(user1);
                    request.getSession().setAttribute("admin", users.get(0));
                    Loginip loginip = new Loginip();
                    loginip.setAddr(utilController.getIpAddr(request));
                    loginip.setSavetime(Info.getDateStr());
                    loginip.setName(users.get(0).getTname());
                    String content = "用户【" + users.get(0).getTname() + "】于" + Info.getDateStr() + "登录系统！";
                    loginip.setContent(content);
                    loginipService.insert(loginip);

                    UUID uuid = UUID.randomUUID();
                    res.put("data", 200);
                    res.put("adminToken", uuid);
                    res.put("sessionadmin", users.get(0));
                    //获取菜单权限
                    Permission permission = new Permission();
                    permission.setRoleid(user3.getRoleid());
                    List<Permission> onelist = permissionService.selectDistinct(permission);
                    //获取当前用户权限
                    List<String> permissionList = new ArrayList<>();
                    Permission permission1 = new Permission();
                    permission1.setRoleid(user3.getRoleid());
                    List<Permission> twolist = permissionService.selectList(permission1);
                    for (Permission permission2 : twolist) {
                        permissionList.add(permission2.getMenusUrl());
                    }
                    res.put("permissionMenusList", onelist);//获取菜单权限
                    res.put("permissionList", permissionList);//获取当前用户权限
                } else {
                    res.put("data", 300);
                }
            }else{
                res.put("data", 600);
            }

        }
        return res;
    }

    /**
     * 退出
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "loginout", method = RequestMethod.POST)
    public HashMap<String, Object> loginout(HttpServletRequest request) {
        HashMap<String, Object> res = new HashMap<>();
        User admin = (User) request.getSession().getAttribute("admin");
        if (admin == null) {
        } else {
            Loginip loginip = new Loginip();
            loginip.setAddr(utilController.getIpAddr(request));
            loginip.setSavetime(Info.getDateStr());
            loginip.setName(admin.getTname());
            String content = "用户【" + admin.getTname() + "】于" + Info.getDateStr() + "退出系统！";
            loginip.setContent(content);
            loginipService.insert(loginip);
        }
        request.getSession().removeAttribute("admin");
        res.put("data", 200);
        return res;
    }


    /**
     * 验证登录名
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "checkUser", method = RequestMethod.POST)
    public ReturnData<List<User>> checkUser(User user) {
        List<User> users = userService.selectList(user);
        if (users.size() != 0) {
            return ReturnData.GET_SUCCESS(users);
        }
        return ReturnData.GET_FAIL();
    }


    /**
     * 验证用户是否登录
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "checkLogin", method = RequestMethod.POST)
    public ReturnData<User> checkLogin(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("admin");
        if (user != null) {
            User admin = userService.selectById(user.getId());
            return ReturnData.GET_SUCCESS(admin);
        }
        return ReturnData.GET_FAIL();
    }


    /**
     * 修改密码
     *
     * @param id
     * @param newupass
     * @param request
     * @return
     */
    @RequestMapping(value = "editupass", method = RequestMethod.POST)
    public ReturnData<User> editupass(Integer id, String newupass, HttpServletRequest request) {
        User admin = userService.selectById(id);
        admin.setUpass(newupass);
        userService.update(admin);
        return ReturnData.GET_SUCCESS(admin);
    }

}


