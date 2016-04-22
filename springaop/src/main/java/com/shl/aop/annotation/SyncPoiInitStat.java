package com.shl.aop.annotation;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * Created by jackson on 16/1/28.
 */
@Component
@Aspect
public class SyncPoiInitStat {

    @Pointcut("@annotation(com.shl.aop.annotation.AspectAnnotation)")
    public void statpoi(){
    }

    @Around("statpoi()")
    public Object aroundExecutePoiStat(ProceedingJoinPoint pJoinPoint) throws Throwable{
        Object res =  pJoinPoint.proceed();
        System.out.println(res);
        return res;
    }
}
