package com.shl.Object;

/**
 * Created by jackson on 16/9/20.
 */
public class ObjectClass {

    public static class AA{
        {
            System.out.println("A");
        }
    }

    public static class Aa{
        {
            System.out.println("a");
        }
    }

    public static void main(String[] args) {

        String name = "shl";
        Object object = name;

        System.out.println(object.getClass());

        AA a = new AA();
        System.out.println(a.getClass());
    }
}
