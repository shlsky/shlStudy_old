package com.shl.classTest;

/**
 * Created by jackson on 16/9/20.
 */
public class ObjectClass {

    public static class Ab {
        {
            System.out.println("A");
        }
    }

    public static class AB {
        {
            System.out.println("a");
        }
    }

    public static void main(String[] args) {

        System.out.println(new AB().getClass());
        System.out.println(new Ab().getClass());
    }
}
