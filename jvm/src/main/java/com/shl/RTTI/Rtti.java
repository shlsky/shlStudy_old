package com.shl.RTTI;

/**
 * Created by jackson on 16/8/27.
 */
public class Rtti {
    public static void main(String[] args) {
        Class<? extends Object> classO = Rtti.class;
        Class<? super Rtti> class1 = Rtti.class;

    }
}
