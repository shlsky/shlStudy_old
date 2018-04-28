/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.shl.objectDiff;

import java.util.Collections;
import java.util.Map;

import de.danielbechler.diff.ObjectDifferBuilder;
import de.danielbechler.diff.node.DiffNode;

/**
 * @author fufeng
 * @version $Id: JavaObjectDiff.java, v 0.1 2017-06-06 下午4:55 fufeng Exp $$
 */
public class JavaObjectDiff {

    public static void main(String[] args) {
        Map<String, String> working = Collections.singletonMap("item", "foo");
        Map<String, String> base = Collections.singletonMap("item", "bar");
        DiffNode diff = ObjectDifferBuilder.buildDefault().compare(working, base);

        System.out.println(diff.toString());
    }
}