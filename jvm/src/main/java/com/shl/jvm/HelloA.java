package com.shl.jvm;

/**
 * Created by jackson on 16/7/22.
 */
public class HelloA {

    public HelloA() {
        System.out.println("HelloA");
    }

    { System.out.println("I'm A class"); }

    static { System.out.println("static A"); }

    protected void sysHello(){
        System.out.println("hello world!");
    }

}