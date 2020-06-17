package com.aaa.entity;

import lombok.Data;

import java.util.List;

/**
 * @Author: 陈建
 * @Date: 2020/6/10 0010 16:10
 * @Version 1.0
 */
@Data
public class LayUiTable {
    private int code;
    private String msg;
    private Integer count;
    private List<?> data;
}
