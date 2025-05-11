package com.it.controller;

import com.it.entity.Member;
import com.it.service.MemberService;
import org.springframework.web.bind.annotation.*;
import com.it.common.ReturnData;
import com.it.common.page.PageResult;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/member")
public class MemberController {
    /**
     * 服务对象
     */
    @Resource
    private MemberService memberService;

    /**
     * 通过主键查询单条数据
     *
     * @param member 参数对象
     * @return 单条数据
     */
    @RequestMapping(value = "get", method = RequestMethod.POST)
    public ReturnData<Member> selectOne(Member member) {
        Member result = memberService.selectById(member.getId());
        if(result != null){
            return ReturnData.GET_SUCCESS(result);
        }
        return ReturnData.GET_FAIL();
    }
    
    /**
     * 新增一条数据
     *
     * @param member 实体类
     * @return Response对象
     */
    @RequestMapping(value = "insert", method = RequestMethod.POST)
    public ReturnData<Member> insert(Member member) {
        int result = memberService.insert(member);
        if (result > 0) {
            return ReturnData.ADD_SUCCESS();
        }
        return ReturnData.ADD_FAIL();
    }

    /**
     * 修改一条数据
     *
     * @param member 实体类
     * @return Response对象
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public ReturnData<Member> update(Member member) {
        Member result = memberService.update(member);
        if (result != null) {
            return ReturnData.UPDATE_SUCCESS(result);
        }
        return ReturnData.UPDATE_FAIL();
    }

    /**
     * 删除一条数据
     *
     * @param member 参数对象
     * @return Response对象
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public ReturnData<Member> delete(Member member) {
        int result = memberService.deleteById(member.getId());
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
    public ReturnData<List<Member>> selectAll(Member member) {
        List<Member> members = memberService.selectList(member);
        if (members != null) {
            return ReturnData.GET_SUCCESS(members);
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
    public ReturnData<PageResult> selectPage(Integer start, Integer limit, Member member) {
        PageResult pageResult = memberService.selectPage(start, limit, member);
        if (pageResult != null) {
            return ReturnData.GET_SUCCESS(pageResult);
        }
        return ReturnData.GET_FAIL();
    }

    /**
     * 修改密码
     *
     * @param member 实体类
     * @return Response对象
     */
    @RequestMapping(value = "updateUpass", method = RequestMethod.POST)
    public HashMap<String,Object> updateUpass(Member member) {
        HashMap<String,Object> res = new HashMap<String,Object>();
        Member member1 = memberService.selectById(member.getId());
        if(member1.getUpass().equals(member.getUpass())){
            member1.setUpass(member.getNewsupass());
            memberService.update(member1);
            res.put("data",200);
        }else{
            res.put("data",400);
        }
        return res;
    }

    /**
     * 登录
     * @param member
     * @param request
     * @return
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public HashMap<String,Object> login(Member member, HttpServletRequest request) {
        HashMap<String,Object> res = new HashMap<String,Object>();
        List<Member> members = memberService.selectList(member);
        if(members.size()==0){
            res.put("data",400);
        }else{
            if(members.get(0).getStatus().equals("正常")){
                request.getSession().setAttribute("sessionmember", members.get(0));
                res.put("data",200);
                res.put("sessionmember",members.get(0));
            }else{
                res.put("data",300);
            }
        }
        return res;
    }


    /**
     * 退出
     * @param request
     */
    @RequestMapping(value = "logout", method = RequestMethod.POST)
    public void logout(HttpServletRequest request) {
        request.getSession().removeAttribute("sessionmember");
    }
}


