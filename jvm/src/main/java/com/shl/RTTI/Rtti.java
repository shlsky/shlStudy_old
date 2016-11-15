package com.shl.RTTI;

/**
 * Created by jackson on 16/8/27.
 */
public class Rtti {

    private Integer value = 1;

    public InnerClass getInnerClass(){
        return new InnerClass();
    }

    public class InnerClass{

        public void pringValue(){
            System.out.println(value);
        }
    }
}
