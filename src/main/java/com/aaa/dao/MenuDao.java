package com.aaa.dao;

import com.aaa.entity.Menu;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 菜单权限表 Mapper 接口
 * </p>
 *
 * @author AAATeacherChen
 * @since 2020-06-09
 */
@Mapper
@Repository
public interface MenuDao extends BaseMapper<Menu> {
    /**
     * create by: Teacher陈
     * description: 传入登录名字，返回登录名对应的所有菜单
     * create time: 2020/6/17 14:46
     *
     * @return a
     * @Param: null
    */
    List<Menu> findMenusByLoginName(@Param("loginName") String loginName);

}
