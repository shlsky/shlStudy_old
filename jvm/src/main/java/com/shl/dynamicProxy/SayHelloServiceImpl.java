package com.shl.dynamicProxy;

/**
 * Created by jackson on 16/8/29.
 */
public class SayHelloServiceImpl implements ISayHelloService {
    @Override
    public void sysHello() {
        System.out.println("hello");
    }

    @Override
    public void sysBye() {
        System.out.println("bye");
    }
}
