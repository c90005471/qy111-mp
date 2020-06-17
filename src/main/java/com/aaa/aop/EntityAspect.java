package com.aaa.aop;

import com.aaa.util.AAAConstants;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Date;

/**
 * @Author: 陈建
 * @Date: 2020/6/13 0013 10:32
 * @Version 1.0
 * 补充添加修改信息
 */
@Aspect
@Component
public class EntityAspect {
    @Pointcut("@annotation(com.aaa.aop.SaveOrUpdateEntityAnn)")
    public void pointCutSaveOrUpdate(){}
    @Around("pointCutSaveOrUpdate()")
    /**
     * create by: Teacher陈
     * description: 通过环绕方法将controller中的保存和修改方法统一
     * 加上时间和人
     * create time: 2020/6/17 10:
     *
     * @Param: joinPoint
     * @return java.lang.Object
     */
    public Object aroundMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        //获取所有的参数
        Object[] joinPointArgs = joinPoint.getArgs();
        //获取方法的签名
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        //获取方法
        Method method = signature.getMethod();
        String methodName= method.getName();
        //获取注解
        SaveOrUpdateEntityAnn annotation = method.getAnnotation(SaveOrUpdateEntityAnn.class);
        //获取注解的值
        String typeName = annotation.typeName();
        //根据类名反射实例化对象
        Class<?> aClass = Class.forName(typeName);
        for (int i = 0; i <joinPointArgs.length ; i++) {
            Object fromValue=joinPointArgs[i];
            ObjectMapper objectMapper = new ObjectMapper();
            Object o = objectMapper.convertValue(fromValue, aClass);

            if(methodName.contains(AAAConstants.SAVE_OPERATION)){
                Method setCreateTime = aClass.getMethod("setCreateTime", Date.class);
                setCreateTime.invoke(o,new Date());
            }
            if(methodName.contains(AAAConstants.UPDATE_OPERATION)){
                Method setUpdateTime = aClass.getMethod("updateTime", Date.class);
                setUpdateTime.invoke(o,new Date());
            }
            System.out.println(o.toString());
            joinPointArgs[i]=o;
        }
        Object proceed = joinPoint.proceed(joinPointArgs);
        return proceed;


    }

}
