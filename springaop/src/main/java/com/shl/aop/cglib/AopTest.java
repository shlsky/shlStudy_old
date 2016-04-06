package com.shl.aop.cglib;

/**
 * Created by jackson on 16/2/19.
 */
public class AopTest {

    public static void main(String[] args) {
        CglibProxy proxy = new CglibProxy();
        //通过生成子类的方式创建代理类
        SayHello proxyImp = (SayHello)proxy.getProxy(SayHello.class);
        proxyImp.say();
        proxyImp.say1();
        proxyImp.say2();
    }
}
