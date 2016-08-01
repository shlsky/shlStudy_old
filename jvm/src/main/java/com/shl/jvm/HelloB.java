package com.shl.jvm;

/**
 * Created by jackson on 16/7/22.
 */
public class HelloB extends HelloA {
    public HelloB() {
        System.out.println("HelloB");
    }

    { System.out.println("I'm B class"); }

    static { System.out.println("static B");
    }

    public static void main(String[] args) {
        new HelloB();
        System.out.println("-----");
        new HelloB().sysHello();
    }
}