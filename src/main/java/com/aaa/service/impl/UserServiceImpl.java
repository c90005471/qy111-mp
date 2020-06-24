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
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
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
    public List<UserVo> selectUserVoList( Page<UserVo> pageInfo, Map<String ,Object> condition) {
        List<UserVo> userVoList = userDao.selectUserVoList(pageInfo, condition);
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
        String roleIds = user.getSalt();
        //生成随机码作为salt
        String salt = UUID.randomUUID().toString();
        //获取明文密码
        String message = user.getPassword();
        //获取密文密码
        String encryption = ShiroUtil.encryptionBySalt(salt, message);
        //设置user的密码属性
        user.setPassword(encryption);
        //设置user对象的盐值属性
        user.setSalt(salt);
        //将用户保存到数据库
        Integer insert = userDao.insert(user);
        //如果插入成功，并且页面传入的角色id不为空
        if (insert > 0 && roleIds != null) {
            //删除此用户原有的角色
            Wrapper<UserRole> wrapper = new EntityWrapper();
            wrapper.eq("user_id", user.getUserId());
            userRoleDao.delete(wrapper);
            //添加此用户关联的新角色
            String[] strings = roleIds.split(",");
            for (String string : strings) {
                if (string != null && string != "") {
                    Integer roleID = Integer.parseInt(string);
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
