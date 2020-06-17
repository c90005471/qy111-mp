package com.aaa.controller;


import com.aaa.aop.SaveOrUpdateEntityAnn;
import com.aaa.entity.Dept;
import com.aaa.entity.LayUiTable;
import com.aaa.service.DeptService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * <p>
 * 部门表 前端控制器
 * </p>
 *
 * @author AAATeacherChen
 * @since 2020-06-09
 */
@Controller
@RequestMapping("/dept")
public class DeptController {
    @Autowired
    private DeptService deptService;
    @RequestMapping("/toShowDept")
    /**
     * create by: Teacher陈
     * description:请求ShowDept，跳转到显示所有部门信息的页面
     * create time: 2020/6/17 16:28
     *
     * @Param: 
     * @return java.lang.String
     */
    public String toShowDept(){
            return "dept/showDept";
    }
    @RequestMapping("/selectAllDept")
    @ResponseBody
    public LayUiTable selectAllDept(Integer page,Integer limit,String searchDeptName   ,String searchCreateUser ,String searchUpdateUser ){
        LayUiTable table= new LayUiTable();
        //返回所有条数
        Wrapper<Dept> wrapper = new EntityWrapper<>();

        //添加模糊查询的条件
        if(null!=searchDeptName&&!"".equals(searchDeptName)){
            wrapper.like("dept_name",searchDeptName);
        }
        if(null!=searchCreateUser&&!"".equals(searchCreateUser)){
            wrapper.like("create_by",searchCreateUser);
        }
        if(null!=searchUpdateUser&&!"".equals(searchUpdateUser)){
            wrapper.like("update_by",searchUpdateUser);
        }
        //sql语句的条件，是从后往前执行，（select * from user where username =aaa and password=bbb）
        //什么样的条件放后面？凡事能够大量排除数据的条件放后面
        wrapper.eq("del_flag",0);
        int deptListCount = deptService.selectCount(wrapper);
        Page<Dept> pageInfo = new Page(page,limit);
        Page<Dept> deptPage = deptService.selectPage(pageInfo,wrapper);
        //从分页结果中提取list集合
        List<Dept> deptList = deptPage.getRecords();
        table.setCode(0);
        table.setMsg("操作成功");
        table.setData(deptList);
        table.setCount( deptListCount);
        return table;
    }

    /**
     * 保存部门
     * @param dept
     * @return
     */
    @SaveOrUpdateEntityAnn(typeName="com.aaa.entity.Dept")
    @RequestMapping("/saveDept")
    @ResponseBody
    public Object saveDept(Dept dept){
        Map<String ,Object > map = new HashMap<>();
        dept.setCreateBy("root");
        dept.setCreateTime(new Date());
        boolean insert = deptService.insert(dept);
        if(insert){
            map.put("code",0);
            map.put("msg","保存成功");
        }else {
            map.put("code",1);
            map.put("msg","保存失败");
        }
        return map;
    }

    /**
     * 更新部门信息（controller中的方法作用1：页面跳转，2：收受参数，返回参数
     * 不建议在controller中加入过多的业务逻辑）
     * @param dept
     * @return
     */
    @RequestMapping("/updateDept")
    @ResponseBody
    public Object updateDept(Dept dept){
        Map<String ,Object > map = new HashMap<>();
        dept.setUpdateBy("root");
        dept.setUpdateTime(new Date());
        //此处待优化
        if(null==dept.getStatus()){
            dept.setStatus("1");
        }
        boolean update = deptService.updateById(dept);
        if(update){
            map.put("code",0);
            map.put("msg","修改成功");
        }else {
            map.put("code",1);
            map.put("msg","修改失败");
        }

        return map;
    }
    @RequestMapping("/deleteDept")
    @ResponseBody
    public Object deleteDept(Dept dept){
        Map<String ,Object > map = new HashMap<>();
        //此处是逻辑删除，修改delflag
        dept.setDelFlag("1");
        boolean update = deptService.updateById(dept);

        if(update){
            map.put("code",0);
            map.put("msg","删除成功");
        }else {
            map.put("code",1);
            map.put("msg","删除失败");
        }
        return map;
    }
    @RequestMapping(value = "/deleteBatchDept")
    @ResponseBody
    public Object deleteBatchDept(@RequestBody List<Dept> deptList){
       Map<String ,Object > map = new HashMap<>();
        List<Dept> deptListNew=new ArrayList<>();
        //此处是逻辑删除，修改delflag
        for (Dept dept : deptList) {
            Dept deptNew= new Dept();
            deptNew.setDelFlag("1");
            deptNew.setDeptId(dept.getDeptId());
            deptListNew.add(deptNew);
        }
        boolean update = deptService.updateBatchById(deptListNew);
        if(update){
            map.put("code",0);
            map.put("msg","删除成功");
        }else {
            map.put("code",1);
            map.put("msg","删除失败");
        }
        return map;
    }

}

