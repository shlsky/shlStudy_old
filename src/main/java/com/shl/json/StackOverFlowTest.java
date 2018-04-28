/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.shl.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * @author fufeng
 * @version $Id: StackOverFlowTest.java, v 0.1 2017-09-12 上午10:59 fufeng Exp $$
 */
public class StackOverFlowTest {

    public static void main(String[] args) {
        Person person = new Person("shl","26");

        System.out.println(JSON.toJSONString(person, SerializerFeature.WriteClassName, SerializerFeature.DisableCircularReferenceDetect));
    }
}