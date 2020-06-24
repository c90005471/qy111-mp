package com.aaa.service;

import com.aaa.entity.User;
import com.aaa.entity.UserVo;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户信息表 服务类
 * </p>
 *
 * @author AAATeacherChen
 * @since 2020-06-09
 */
public interface UserService extends IService<User> {

    List<UserVo> selectUserVoList( Page<UserVo> pageInfo, Map<String ,Object> condition);
    boolean saveUserAndSalt(User user);

}
