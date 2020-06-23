package com.aaa.controller;


import com.aaa.aop.SaveOrUpdateEntityAnn;
import com.aaa.entity.*;
import com.aaa.service.DeptService;
import com.aaa.service.RoleService;
import com.aaa.service.UserService;
import com.aaa.util.MyConstants;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.websocket.server.PathParam;
import java.util.List;

/**
 * <p>
 * 用户信息表 前端控制器
 * </p>
 *
 * @author AAATeacherChen
 * @since 2020-06-09
 */
@Controller
@RequestMapping("/user")
public class UserController  extends  BaseController{
    @Autowired
    UserService userService;
    @Autowired
    DeptService deptService;
    @Autowired
    RoleService roleService;

    @RequestMapping("/toShowUser")
    public String toShowUser(Model model){
        //查询所有的部门信息，填充到页面下拉框中
        List<Dept> deptList = deptService.selectList(null);
        List<Role> roleList = roleService.selectList(null);
        model.addAttribute("deptList", deptList);
        model.addAttribute("roleList", roleList);
        return "user/showUser";
    }
    /**
     * create by: Teacher陈
     * description: 返回所有的用户信息，包含部门信息
     * create time: 2020/6/20 17:20
     *
     * @Param:
     * @return java.lang.String
     */
    @RequestMapping("/selectAllUser")
    @ResponseBody
    public LayUiTable selectAllUser(){

        LayUiTable layUiTable = new LayUiTable();
        List<UserVo> userVoList = userService.selectUserVoList();
        layUiTable.setCode(0);
        layUiTable.setCount(userVoList.size());
        layUiTable.setData(userVoList);
        layUiTable.setMsg("操作成功");
        return layUiTable;
    }


    /**
     * create by: Teacher陈
     * description: 判断用户名是否存在，存在的话返回false，不存在返回true
     * create time: 2020/6/22 17:08
     *
     * @Param: username
     * @return boolean
     */
    @RequestMapping("/checkUserName")
    @ResponseBody
    public Result checkUserName(String username){
        Wrapper<User> wrapper = new EntityWrapper<>();
        User user = userService.selectOne(wrapper.eq("login_name", username));
        if (user != null) {
           return  super.error(MyConstants.OPERATION_FAIL_CODE, username+"已经被占用");
        }
        return super.success();
    }


    /**
     * create by: Teacher陈
     * description: 保存用户
     * create time: 2020/6/23 15:43
     *
     * @return a
     * @Param: null
    */
    @RequestMapping("/saveUser")
    @ResponseBody
    @SaveOrUpdateEntityAnn(entityClass = User.class)
    public Result saveUser(User user){
        boolean insert = userService.saveUserAndSalt(user);
        if(insert){
            return success();
        }
        return error();
    }
}

