package com.shl.jvm;

/**
 * Created by jackson on 16/7/22.
 */
public class HelloB extends HelloA {

    public static void demaic(){
        System.out.println("demaic helloB");
    }
    public HelloB() {
        System.out.println("HelloB");
    }

    { System.out.println("I'm B class"); }

    static { System.out.println("static B");
    }

    public static void main(String[] args) {
        HelloA helloA = new HelloB();
        helloA.demaic();
        new HelloB().sysHello();
    }
}