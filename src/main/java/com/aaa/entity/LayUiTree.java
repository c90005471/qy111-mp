package com.aaa.entity;

import lombok.Data;

import java.util.List;

/**
 * layui使用的树形菜单需要的实体对象
 */
@Data
public class LayUiTree {


    /**
     * title : 一级1
     * id : 1
     * field : name1
     * checked : true
     * spread : true
     * children : [{"title":"二级1-1 可允许跳转","id":3,"field":"name11","href":"https://www.layui.com/","children":[{"title":"三级1-1-3","id":23,"field":"","children":[{"title":"四级1-1-3-1","id":24,"field":"","children":[{"title":"五级1-1-3-1-1","id":30,"field":""},{"title":"五级1-1-3-1-2","id":31,"field":""}]}]},{"title":"三级1-1-1","id":7,"field":"","children":[{"title":"四级1-1-1-1 可允许跳转","id":15,"field":"","href":"https://www.layui.com/doc/"}]},{"title":"三级1-1-2","id":8,"field":"","children":[{"title":"四级1-1-2-1","id":32,"field":""}]}]},{"title":"二级1-2","id":4,"spread":true,"children":[{"title":"三级1-2-1","id":9,"field":"","disabled":true},{"title":"三级1-2-2","id":10,"field":""}]},{"title":"二级1-3","id":20,"field":"","children":[{"title":"三级1-3-1","id":21,"field":""},{"title":"三级1-3-2","id":22,"field":""}]}]
     */

    private String title;
    private int id;
    private String field;
    private boolean checked;
    private boolean spread;
    private  String url;
    private List<LayUiTree> children;


}
