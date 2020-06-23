package com.aaa.service.impl;

import com.aaa.dao.UserRoleDao;
import com.aaa.entity.User;
import com.aaa.dao.UserDao;
import com.aaa.entity.UserRole;
import com.aaa.entity.UserVo;
import com.aaa.service.UserService;
import com.aaa.shiro.ShiroUtil;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

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
    private UserDao userDao;
    @Autowired
    private UserRoleDao userRoleDao;

    @Override
    public List<UserVo> selectUserVoList() {
        List<UserVo> userVoList = userDao.selectUserVoList();
        return userVoList;
    }

    /**
     * create by: Teacher陈
     * description: 保存用户之前，对密码进行加盐加密
     * create time: 2020/6/23 16:05
     *
     * @return a
     * @Param: null
     */
    @Override
    public boolean saveUserAndSalt(User user) {
        //获取从页面传入的角色id,因为salt是在后台生成，所有由salt属性传入角色id的集合
        String roleIds=user.getSalt();
        String salt = UUID.randomUUID().toString();
        String message = user.getPassword();
        String encryption = ShiroUtil.encryptionBySalt(salt, message);
        user.setPassword(encryption);
        user.setSalt(salt);
        Integer insert = userDao.insert(user);
        if (insert > 0) {
            //删除此用户原有的角色
            Wrapper<UserRole> wrapper = new EntityWrapper();
            wrapper.eq("user_id", user.getUserId());
            userRoleDao.delete(wrapper);
            //添加此用户关联的新角色
            String[] strings = roleIds.split(",");
            for (String string : strings) {
                if (string != null&&string!="") {
                    Integer roleID= Integer.parseInt(string);
                    UserRole userRole = new UserRole();
                    userRole.setUserId(user.getUserId());
                    userRole.setRoleId(roleID);
                    userRoleDao.insert(userRole);
                }

            }

            return true;
        }
        return false;

    }
}
