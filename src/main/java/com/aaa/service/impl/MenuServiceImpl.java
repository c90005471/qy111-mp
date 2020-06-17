package com.aaa.service.impl;

import com.aaa.entity.LayUiTree;
import com.aaa.entity.Menu;
import com.aaa.dao.MenuDao;
import com.aaa.service.MenuService;
import com.aaa.util.TreeUtil;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
