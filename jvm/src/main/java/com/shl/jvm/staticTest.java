package com.shl.jvm;

import lombok.Data;

/**
 * Created by jackson on 16/5/13.
 */
public class StaticTest {

    static int mm =1;
    {
        mm =7;
        System.out.println(mm);
    }
    static {
        System.out.println(mm);
        mm = 3;
        System.out.println(mm);
    }

    public StaticTest(int i){
        mm = i;
        System.out.println("init..."+mm);
    }


    public static void main(String[] args) {

        StaticTest test1 = new StaticTest(5);
//        StaticTest test2 = new StaticTest(6);
    }
}
