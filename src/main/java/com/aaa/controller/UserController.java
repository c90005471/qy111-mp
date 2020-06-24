package com.aaa.controller;


import com.aaa.aop.SaveOrUpdateEntityAnn;
import com.aaa.entity.*;
import com.aaa.service.DeptService;
import com.aaa.service.RoleService;
import com.aaa.service.UserService;
import com.aaa.util.MyConstants;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.websocket.server.PathParam;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        //查询所有的角色信息，填充到页面下拉框中
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
    public LayUiTable selectAllUser(Integer page, Integer limit, String searchLoginName, String searchUserName, String searchPhonenumber){
        LayUiTable table = new LayUiTable();
        //多条件查询所需要的集合
        Map<String ,Object> condition= new HashMap(16);

        Wrapper wrapper = new EntityWrapper();
        //添加模糊查询的条件
        if (null != searchLoginName && !"".equals(searchLoginName)) {
            wrapper.like("login_name", searchLoginName);
            condition.put("login_name", searchLoginName);
        }
        if (null != searchUserName && !"".equals(searchUserName)) {
            condition.put("user_name", searchUserName);
        }
        if (null != searchPhonenumber && !"".equals(searchPhonenumber)) {
            condition.put("phonenumber", searchPhonenumber);
        }
        wrapper.eq("del_flag", 0);
        condition.put("del_flag", 0);
        int userListCount = userService.selectCount(wrapper);
        //如果表中没有数据。则不进行分页查询
        if(userListCount>0){
            Page<UserVo> pageInfo = new Page(page, limit);
            List<UserVo> userVoList = userService.selectUserVoList(pageInfo,condition);
            //从分页结果中提取list集合
            table.setCode(MyConstants.OPERATION_SUCCESS_CODE);
            table.setMsg(MyConstants.OPERATION_SUCCESS_MESSAGE);
            table.setData(userVoList);
            table.setCount(userListCount);
            return table;
        }else
        {

            return table;
        }

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

