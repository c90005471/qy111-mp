package com.aaa.controller;


import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;

/**
 * <p>
 * 角色信息表 前端控制器
 * </p>
 *
 * @author AAATeacherChen
 * @since 2020-06-09
 */
@Controller
@RequestMapping("/role")
public class RoleController {
    @RequestMapping("/toShowRole")
    /**
     * create by: Teacher陈
     * description:请求ShowDept，跳转到显示权限树的页面
     * create time: 2020/6/17 16:28
     *
     * @Param:
     * @return java.lang.String
     */
    public String toShowRole(){
        return "role/showRole";
    }
}

