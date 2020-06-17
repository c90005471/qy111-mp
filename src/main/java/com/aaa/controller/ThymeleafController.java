package com.aaa.controller;

import com.aaa.entity.Dept;
import com.aaa.service.DeptService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Author: 陈建
 * @Date: 2020/6/10 0010 14:48
 * @Version 1.0
 */
@Controller
public class ThymeleafController {
    @Autowired
    private DeptService deptService;
    @RequestMapping("/toHelloPage")
    public String toHelloPage(Model model, HttpSession session){
        //往request中存储数据
        model.addAttribute("username","chenjian ");
        //  往session中存储数据
        session.setAttribute("password" ,"123456");
        Wrapper<Dept> wrapper = new EntityWrapper<>();
        wrapper.eq("del_flag",0);
        List<Dept> deptList = deptService.selectList(wrapper);
        //deptList=null;
        model.addAttribute("deptList",deptList);
        model.addAttribute("deptid",888);
        return "hello";
    }

    @RequestMapping("/tohelloLayUi")
    public String tohelloLayUi(){
        return "helloLayUi";
    }
}
