package com.aaa.aop;

import java.lang.annotation.*;

/**
 * @Author: 陈建
 * @Date: 2020/6/13 0013 10:37
 * @Version 1.0
 * 此注解使用的场合是在需要匹配创建人和创建时间，
 * 或者修改人和修改时间的方法上。
 */
//此注解使用在方法上
@Target(ElementType.METHOD)
//注解在运行的时候生效
@Retention(RetentionPolicy.RUNTIME)
public @interface SaveOrUpdateEntityAnn {
    //类的全限定名称
    String typeName() ;
}
