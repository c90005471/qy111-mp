package com.aaa;

import com.aaa.entity.Dept;
import com.aaa.service.DeptService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class Qy111MpApplicationTests {
    @Autowired
    private DeptService deptService;

    @Test
    void saveDept() {
        Dept dept = new Dept();
        dept.setDeptName("学术部");
        deptService.insert(dept);
        deptService.insert(dept);
        deptService.insert(dept);
        deptService.insert(dept);
        deptService.insert(dept);
        deptService.insert(dept);
        deptService.insert(dept);
        deptService.insert(dept);
        deptService.insert(dept);
        deptService.insert(dept);
        deptService.insert(dept);
        deptService.insert(dept);
        deptService.insert(dept);
        deptService.insert(dept);
        deptService.insert(dept);
        deptService.insert(dept);
        deptService.insert(dept);
        deptService.insert(dept);
        deptService.insert(dept);
    }
    @Test
    void deleteDept() {
       /* Dept dept = new Dept();
        dept.setDeptName("学术部");*/
       //按照条件删除
      /*  Wrapper<Dept> wrapper= new EntityWrapper<>();
        wrapper.eq("dept_name","学术部");
        deptService.delete(wrapper);*/
        //按照主键批量删除boolean deleteBatchIds(Collection<? extends Serializable> var1);
/*        List<Integer> ids= new ArrayList<>();
        ids.add(108);
        ids.add(109);
        deptService.deleteBatchIds(ids);*/
        //安装主键单个删除
       deptService.deleteById(110);

    }

    @Test
    void updateDept() {
        Dept dept = new Dept();
        dept.setDeptId(111);
        dept.setDeptName("军机处");
        deptService.updateById(dept);
    }

    /**
     * 测试排序
     */
    @Test
    void OrderDept() {
        Wrapper<Dept> wrapper= new EntityWrapper<>();
        wrapper.orderBy("order_num",false);
        List<Dept> deptList = deptService.selectList(wrapper);
        for (Dept dept : deptList) {
            System.out.println(dept.toString());
        }
    }
    /**
     * 测试模糊查询
     */
    @Test
    void likeDept() {
        Wrapper<Dept> wrapper= new EntityWrapper<>();
        wrapper.like("dept_name","市场");
        List<Dept> deptList = deptService.selectList(wrapper);
        for (Dept dept : deptList) {
            System.out.println(dept.toString());
        }
    }
    /**
     * 分页查询
     */
    @Test
    void pageDept() {
        /**
         *     public Page(int current, int size) {
         *         super(current, size);
         *         current 当前第几页
         *         size是一页显示多少行
         *         this.records = Collections.emptyList();
         *     }
         */
        Page<Dept> page = new Page(2,3);
        Page<Dept> deptPage = deptService.selectPage(page);
        //从分页结果中提取list集合
        List<Dept> deptList = deptPage.getRecords();
        for (Dept dept : deptList) {
            System.out.println(dept.toString());
        }
    }

}
