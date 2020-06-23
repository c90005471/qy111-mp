package com.aaa.dao;

import com.aaa.entity.UserRole;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 用户和角色关联表 Mapper 接口
 * </p>
 *
 * @author AAATeacherChen
 * @since 2020-06-09
 */
@Mapper
@Repository
public interface UserRoleDao extends BaseMapper<UserRole> {

}
