package com.chuanchiai.aop;

import com.chuanchiai.dynamicRouting.DataSourceContextHolder;
import com.chuanchiai.dynamicRouting.DataSourceSelector;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;


/**
 * Created by chuanchiai on 2020/7/18
 */
@Component
@Slf4j
@Aspect
public class DataSourceAop {

    @Around("@annotation(com.chuanchiai.dynamicRouting.DataSourceSelector)")
    public Object setDataSource(ProceedingJoinPoint joinPoint) throws Throwable {
        boolean clear = true;
        try {
            Method method = this.getMethod(joinPoint);
            DataSourceSelector annotation = method.getAnnotation(DataSourceSelector.class);
            clear = annotation.clear();
            DataSourceContextHolder.set(annotation.name().getDatasourceName());
            log.info("数据库现已切换至: {}",annotation.name().getDatasourceName());
            return joinPoint.proceed();
        } finally {
            if (clear){
                DataSourceContextHolder.remove();
            }
        }
    }

    public Method getMethod(JoinPoint joinPoint){
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        return signature.getMethod();
    }
}
