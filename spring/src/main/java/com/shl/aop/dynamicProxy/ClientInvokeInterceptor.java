package com.shl.aop.dynamicProxy;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.context.ApplicationContext;

import java.lang.reflect.Method;

/**
 * Created by jackson on 16/4/6.
 */
public class ClientInvokeInterceptor implements MethodInterceptor {
    Object instance;

    public ClientInvokeInterceptor(ApplicationContext applicationContext,Class<?> serviceImpl){

        this.instance = applicationContext.getBean(serviceImpl);
    }
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {

        System.out.println("advice shl shl success!!!");
        invocation.getMethod().invoke(instance,invocation.getArguments());
        System.out.println("advice shl shl success!!!");

        /*
        System.out.println("advice shl shl success!!!");
        System.out.println(invocation.getMethod()+" "+invocation.getArguments()[0]);
        for (Method method : instance.getClass().getDeclaredMethods()){
            if (method.getName().equals(invocation.getMethod().getName())){
                method.invoke(instance,invocation.getArguments());
            }
        }*/

        //invocation.proceed();
        return null;
    }
}
