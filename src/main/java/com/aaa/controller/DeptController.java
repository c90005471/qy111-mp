package com.aaa.controller;


import com.aaa.aop.SaveOrUpdateEntityAnn;
import com.aaa.entity.Dept;
import com.aaa.entity.LayUiTable;
import com.aaa.entity.Result;
import com.aaa.service.DeptService;
import com.aaa.util.MyConstants;
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
public class DeptController extends BaseController {
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
    public String toShowDept() {
        return "dept/showDept";
    }

    @RequestMapping("/selectAllDept")
    @ResponseBody
    public LayUiTable selectAllDept(Integer page, Integer limit, String searchDeptName, String searchCreateUser, String searchUpdateUser) {

        //返回所有条数
        Wrapper<Dept> wrapper = new EntityWrapper<>();

        //添加模糊查询的条件
        if (null != searchDeptName && !"".equals(searchDeptName)) {
            wrapper.like("dept_name", searchDeptName);
        }
        if (null != searchCreateUser && !"".equals(searchCreateUser)) {
            wrapper.like("create_by", searchCreateUser);
        }
        if (null != searchUpdateUser && !"".equals(searchUpdateUser)) {
            wrapper.like("update_by", searchUpdateUser);
        }
        //sql语句的条件，是从后往前执行，（select * from user where username =aaa and password=bbb）
        //什么样的条件放后面？凡事能够大量排除数据的条件放后面
        wrapper.eq("del_flag", 0);
        int deptListCount = deptService.selectCount(wrapper);
        Page<Dept> pageInfo = new Page(page, limit);
        Page<Dept> deptPage = deptService.selectPage(pageInfo, wrapper);
        //从分页结果中提取list集合
        List<Dept> deptList = deptPage.getRecords();
        LayUiTable table = new LayUiTable();
        table.setCode(MyConstants.OPERATION_SUCCESS_CODE);
        table.setMsg(MyConstants.OPERATION_SUCCESS_MESSAGE);
        table.setData(deptList);
        table.setCount(deptListCount);
        return table;
    }

    /**
     * 保存部门
     *
     * @param dept
     * @return
     */
    @SaveOrUpdateEntityAnn(entityClass = Dept.class)
    @RequestMapping("/saveDept")
    @ResponseBody
    public Result saveDept(Dept dept) {
        boolean insert = deptService.insert(dept);
        if (insert) {
            return super.success();
        } else {
            return super.error();
        }
    }

    /**
     * 更新部门信息（controller中的方法作用1：页面跳转，2：收受参数，返回参数
     * 不建议在controller中加入过多的业务逻辑）
     *
     * @param dept
     * @return
     */
    @RequestMapping("/updateDept")
    @ResponseBody
    @SaveOrUpdateEntityAnn(entityClass = Dept.class)
    public Result updateDept(Dept dept) {
        //此处待优化
        if (null == dept.getStatus()) {
            dept.setStatus("1");
        }
        boolean update = deptService.updateById(dept);
        if (update) {
            return super.success();
        } else {
            return super.error();
        }

    }

    @RequestMapping("/deleteDept")
    @ResponseBody
    public Result deleteDept(Dept dept) {
        //此处是逻辑删除，修改delflag
        dept.setDelFlag("1");
        boolean update = deptService.updateById(dept);
        if (update) {
            return super.success();
        } else {
            return super.error();
        }
    }

    @RequestMapping(value = "/deleteBatchDept")
    @ResponseBody
    public Result deleteBatchDept(@RequestBody List<Dept> deptList) {
        List<Dept> deptListNew = new ArrayList<>();
        //此处是逻辑删除，修改delflag
        for (Dept dept : deptList) {
            Dept deptNew = new Dept();
            deptNew.setDelFlag("1");
            deptNew.setDeptId(dept.getDeptId());
            deptListNew.add(deptNew);
        }
        boolean update = deptService.updateBatchById(deptListNew);
        if (update) {
            return super.success();
        } else {
            return super.error();
        }
    }

}

