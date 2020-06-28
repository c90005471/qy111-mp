package com.aaa.service;

import com.aaa.entity.LayUiTree;
import com.aaa.entity.Menu;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 菜单权限表 服务类
 * </p>
 *
 * @author AAATeacherChen
 * @since 2020-06-09
 */
public interface MenuService extends IService<Menu> {

     List<LayUiTree> findMenus(String loginName);
     Set<String> findAllMenusByLoginName(String loginName);

}
