package com.aaa.util;

import com.aaa.entity.LayUiTree;
import com.aaa.entity.Menu;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ：Teacher陈
 * @date ：Created in 2020/6/17 15:30
 * @description：树状菜单工具类
 * @modified By：
 * @version: 1.0
 */
public class TreeUtil {
    /**
     * create by: Teacher陈
     * description:将List<Menu>换成 List<LayUiTree>
     * create time: 2020/6/17 15:32
     *
     * @return  List<LayUiTree>
     * @Param: menuList
    */
    public static List<LayUiTree> fromMenuListToTreeList(List<Menu> menuList){
        List<LayUiTree>   treeList = new ArrayList<>();
        //遍历所有的menu对象，然后发现menu对象有孩子，就继续便利孩子，递归操作
        for (Menu menu : menuList) {
            //如果父亲的id为0，就说明是一级目录
            if(menu.getParentId()==0){
                //将menu转成tree对象
                LayUiTree tree=fromMenuToTree(menu);
                //找自己的孩子,给tree对象设置孩子children
                LayUiTree treeChilren = setTreeChilren(tree, menuList);
                treeList.add(treeChilren);
            }
        }
        return treeList;
    }


    /**
     * create by: Teacher陈
     * description:找自己的孩子,给tree对象设置孩子children
     * create time: 2020/6/17 15:44
     *
     * @return a
     * @Param: null
    */
    public static LayUiTree  setTreeChilren( LayUiTree tree,List<Menu> menuList){
        //此集合封装所有的孩子
        List<LayUiTree> children = new ArrayList<>();
        for (Menu menu : menuList) {
            //tree的id是他所有孩子的父亲id
            if(menu.getParentId()==tree.getId())
            {
                //将menu转成tree对象
                LayUiTree layUiTree=fromMenuToTree(menu);
                children.add(setTreeChilren(layUiTree,menuList));
            }
        }
        tree.setChildren(children);
        return tree;
    }

    /**
     * create by: Teacher陈
     * description: 将menu转成tree对象
     * create time: 2020/6/17 15:55
     *
     * @return a
     * @Param: null
    */
    public static LayUiTree  fromMenuToTree(Menu menu){
        LayUiTree layUiTree = new LayUiTree();
        layUiTree.setId(menu.getMenuId());
        layUiTree.setTitle(menu.getMenuName());
        layUiTree.setUrl(menu.getUrl());
        return layUiTree;
    }
}
