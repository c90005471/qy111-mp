package com.aaa.service.impl;

import com.aaa.entity.User;
import com.aaa.dao.UserDao;
import com.aaa.entity.UserVo;
import com.aaa.service.UserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author AAATeacherChen
 * @since 2020-06-09
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {
    @Autowired
    private  UserDao userDao;
    @Override
    public List<UserVo> selectUserVoList() {
        List<UserVo> userVoList = userDao.selectUserVoList();
        return userVoList;
    }
}
