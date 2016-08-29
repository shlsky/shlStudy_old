package com.shl.dynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by jackson on 16/8/29.
 */
public class DynamicProxy implements InvocationHandler {
    ISayHelloService proxied;
    public DynamicProxy(ISayHelloService proxied){
        this.proxied = proxied;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println("DynamicProxy invoke!");
        //下面两句会导致StackOverflow
        //ISayHelloService sayHelloService1 = (ISayHelloService)proxy;
        //sayHelloService1.sysHello();
        return method.invoke(proxied,args);
    }

    public static void main(String[] args) {
        ISayHelloService sayHelloService = new SayHelloServiceImpl();

        ISayHelloService sayHelloService1 = (ISayHelloService) Proxy.newProxyInstance(ISayHelloService.class.getClassLoader(),
                new Class[]{ISayHelloService.class},
                new DynamicProxy(sayHelloService));
        sayHelloService1.sysBye();
    }
}
