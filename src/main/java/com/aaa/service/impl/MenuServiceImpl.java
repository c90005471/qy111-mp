package com.aaa.service.impl;

import com.aaa.entity.LayUiTree;
import com.aaa.entity.Menu;
import com.aaa.dao.MenuDao;
import com.aaa.service.MenuService;
import com.aaa.util.TreeUtil;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * 菜单权限表 服务实现类
 * </p>
 *
 * @author AAATeacherChen
 * @since 2020-06-09
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuDao, Menu> implements MenuService {
    @Autowired
    private MenuDao menuDao;
    @Override
    /**
     * create by: Teacher陈
     * description: 根据登录名字查询树状菜单
     * create time: 2020/6/17 14:51
     *
     * @Param: loginName
     * @return java.util.List<com.aaa.entity.LayUiTree>
     */
    public List<LayUiTree> findMenus(String loginName) {
        //查询出所有的menu
        List<Menu> menuList = menuDao.findMenusByLoginName(loginName);
        //将List<Menu>换成 List<LayUiTree>
        List<LayUiTree> treeList = TreeUtil.fromMenuListToTreeList(menuList);
        return treeList;
    }

    /**
     * create by: Teacher陈
     * description: 根据登录用户名放回此用户关联的所有权限关键字
     * create time: 2020/6/28 16:22
     *
     * @return a
     * @Param: null     */
    @Override
    public Set<String> findAllMenusByLoginName(String loginName){
        //查询出所有的menu
        List<Menu> menuList = menuDao.findMenusByLoginName(loginName);
        //过滤重复关键字
        Set<String> perms=new HashSet<String>();
        for (Menu menu : menuList) {
            if (menu.getPerms() != null&&menu.getPerms().length()>0) {
                perms.add(menu.getPerms());
            }
        }
        return perms;
    }
}
