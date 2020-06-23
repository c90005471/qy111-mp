package com.aaa.aop;

import com.aaa.entity.User;
import com.aaa.util.MyConstants;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.shiro.SecurityUtils;
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
     * description: 通过AOP环绕方法将controller中的保存和修改方法统一
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
        Class<?> entityClass = annotation.entityClass();
        //遍历所有的参数，并修改参数的内容
        for (int i = 0; i <joinPointArgs.length ; i++) {
            Object fromObject=joinPointArgs[i];
            //通过jackson工具转换对象
            ObjectMapper objectMapper = new ObjectMapper();
            //将原始对象，通过jakson工具转换成具体的实体对象
            Object toObject = objectMapper.convertValue(fromObject, entityClass);
            //获取当前登录的用户
            User user = (User) SecurityUtils.getSubject().getPrincipal();
            String loginName = user.getLoginName();
            //如果是保存方法，自动匹配创建人和创建时间
            if(methodName.contains(MyConstants.SAVE_OPERATION)){
                Method setCreateTime = entityClass.getMethod("setCreateTime", Date.class);
                setCreateTime.invoke(toObject,new Date());
                Method setCreateBy = entityClass.getMethod("setCreateBy",String.class);
                setCreateBy.invoke(toObject,loginName);

            }
            //如果是修改方法，自动匹配修改人和修改时间
            if(methodName.contains(MyConstants.UPDATE_OPERATION)){
                Method setUpdateTime = entityClass.getMethod("setUpdateTime", Date.class);
                setUpdateTime.invoke(toObject,new Date());
                Method setUpdateBy = entityClass.getMethod("setUpdateBy",String.class);
                setUpdateBy.invoke(toObject,loginName);
            }
            //将参数修改完之后，再重新设置回去
            joinPointArgs[i]=toObject;
        }
        Object proceed = joinPoint.proceed(joinPointArgs);
        return proceed;


    }

}
