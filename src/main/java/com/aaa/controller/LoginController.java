package com.aaa.controller;


import com.aaa.entity.LayUiTree;
import com.aaa.service.MenuService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class LoginController {

    @Autowired
    private MenuService menuService;
    @RequestMapping("/toLogin")
    public String toLogin(){
        return "login";
    }

    @RequestMapping("/login")
    public   String login(String username,String password,Model model){
       //使用shiro安全框架的认证实现登录逻辑
        //登录验证
        System.out.println(username+password);
        //获取shiro的连接器
        Subject subject = SecurityUtils.getSubject();
        //构建用户登录令牌
        UsernamePasswordToken token= new UsernamePasswordToken(username,password);
        try {
            subject.login(token);
        }catch (UnknownAccountException e){
            model.addAttribute("message","用户名错误");
            return "login";
        }catch (IncorrectCredentialsException e){
            model.addAttribute("message","密码错误");
            return "login";
        }
        //在右上角显示用户名
        model.addAttribute("loginName",username);
        // 在左侧显示树状的菜单导航。根据登录的用户名，查询该用户的所有菜单。
        List<LayUiTree> layUiTreeList = menuService.findMenus(username);
        model.addAttribute("menus", layUiTreeList);
        return "index";
    }
    /**
     * 注销方法
     */
    @RequestMapping("/logout")
    public String logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "login";
    }


}
