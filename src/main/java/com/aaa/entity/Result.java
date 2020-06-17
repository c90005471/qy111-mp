package com.aaa.entity;

import lombok.Data;

/**
 * @author ：Teacher陈
 * @date ：Created in 2020/6/17 10:25
 * @description：统一返回结果信息
 * @modified By：
 * @version: 1.0$
 */
@Data
public class Result<T> {
    /** 返回的和状态码 */
    private Integer code;
    /** 提示信息 */
    private String msg;
    /** 返回的数据 */
    private T data;

}
