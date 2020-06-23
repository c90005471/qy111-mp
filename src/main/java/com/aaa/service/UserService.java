package com.aaa.service;

import com.aaa.entity.User;
import com.aaa.entity.UserVo;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 * 用户信息表 服务类
 * </p>
 *
 * @author AAATeacherChen
 * @since 2020-06-09
 */
public interface UserService extends IService<User> {

    List<UserVo> selectUserVoList();

}
