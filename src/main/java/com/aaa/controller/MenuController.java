package com.aaa.controller;


import com.aaa.entity.LayUiTree;
import com.aaa.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * <p>
 * 菜单权限表 前端控制器
 * </p>
 *
 * @author AAATeacherChen
 * @since 2020-06-09
 */
@Controller
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    private MenuService menuService;

    // List<LayUiTree> layUiTreeList = menuService.findMenus(username);

    @RequestMapping("/findAllMenus")
    @ResponseBody
    /**
     * create by: Teacher陈
     * description: 查询所有的权限菜单
     * create time: 2020/6/17 16:54
     *
     * @Param:
     * @return java.util.List<com.aaa.entity.LayUiTree>
     */
    public List<LayUiTree> findAllMenus(){
        List<LayUiTree> layUiTreeList = menuService.findMenus(null);
        return layUiTreeList;
    }
}

