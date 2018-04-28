/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.shl.Decimal;

import java.math.BigInteger;

/**
 * @author fufeng
 * @version $Id: DecimalConvert.java, v 0.1 2017-07-06 下午12:03 fufeng Exp $$
 */
public class DecimalConvert {

    /**
     * baseString 递归调用
     * @param num 十进制数
     * @param base 要转换成的进制数
     */
    public static String baseString(int num,int base) {
        String str = "", digit = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        if(num == 0){
            return "";
        }else {
            str = baseString(num / base,base);
            return str + digit.charAt(num % base);
        }
    }

    public static String baseString(BigInteger num, int base) {
        String str = "", digit = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        if(num.shortValue() == 0){
            return "";
        }else {
            BigInteger valueOf = BigInteger.valueOf(base);
            str = baseString(num.divide(valueOf),base);
            return str + digit.charAt(num.mod(valueOf).shortValue());
        }
    }

    public static void main(String[] args) {
        System.out.println(baseString(1295,36));

        BigInteger big=new BigInteger("999999999999");
        System.out.println(Thread.currentThread().getId());
    }
}