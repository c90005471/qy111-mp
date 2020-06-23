package com.aaa.controller;

import com.aaa.entity.Result;
import com.aaa.util.MyConstants;

/**
 * @author ：Teacher陈
 * @date ：Created in 2020/6/17 10:36
 * @description：所有的controller的父类
 * @modified By：
 * @version: 1.0
 */
public class BaseController {

    /**
     * create by: Teacher陈
     * description:成功且带数据
     * create time: 2020/6/20 10:17
     *
     * @return a
     * @Param null
     */
    public  Result success(Object object){
        Result result = new Result();
        result.setCode(MyConstants.OPERATION_SUCCESS_CODE);
        result.setMsg(MyConstants.OPERATION_SUCCESS_MESSAGE);
        result.setData(object);
        return result;
    }

    /**
     * @create by: Teacher陈
     * @description: 成功但不带数据
     * @create time: 2020/6/20 10:19
     *
     * @return a
     * @Param: null     */
    public  Result success(){

        return success(null);
    }

    /**
     * create by: Teacher陈
     * description: 失败
     * create time: 2020/6/20 10:18
     *
     * @return a
     * @Param: null     */
    public Result error(Integer code, String msg){
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

    /**
     * create by: Teacher陈
     * description: 失败但默认提示信息
     * create time: 2020/6/20 10:18
     *
     * @return a
     * @Param: null     */
    public Result error(){
        Result result = new Result();
        result.setCode(MyConstants.OPERATION_FAIL_CODE);
        result.setMsg(MyConstants.OPERATION_FAIL_MESSAGE);
        return result;
    }
    /**
     * create by: Teacher陈
     * description: 文件上传下载
     * create time: 2020/6/23 14:03
     *
     * @Param: null
     * @return
     */
    /**
     * create by: Teacher陈
     * description: 统一异常处理
     * create time: 2020/6/23 14:03
     *
     * @Param: null
     * @return
     */

}
