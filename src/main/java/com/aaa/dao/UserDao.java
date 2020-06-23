package com.aaa.dao;

import com.aaa.entity.User;
import com.aaa.entity.UserVo;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 用户信息表 Mapper 接口
 * </p>
 *
 * @author AAATeacherChen
 * @since 2020-06-09
 */
@Mapper
@Repository
public interface UserDao extends BaseMapper<User> {
    List<UserVo> selectUserVoList();
}
