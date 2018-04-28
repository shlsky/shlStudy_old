/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.shl.json;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

/**
 * @author fufeng
 * @version $Id: JSONDiff.java, v 0.1 2017-06-06 下午5:33 fufeng Exp $$
 */
public class JSONDiff {
    public static void main(String[] args) {
        JSONObject jsonObject1 = JSON.parseObject("{\"id\":0,\"name\":\"admin\",\"users\":[{\"id\":2,"
            + "\"name\":[{\"china\":\"shl\"},{\"china\":\"cheng\"}]},{\"id\":3,\"name\":[{\"china\":\"shl\"},"
            + "{\"china\":\"cheng\"}]}]}");

        JSONObject jsonObject2 = JSON.parseObject("{\"id\":0,\"name\":\"admin\",\"users\":[{\"id\":2,"
            + "\"name\":[{\"china\":\"shl\"},{\"china\":\"cheng1\"}]},{\"id\":3,\"name\":[{\"china\":\"shl\"},"
            + "{\"china\":\"cheng\"}]}]}");

        diff(jsonObject1,jsonObject2);
    }

    public static void diff(JSONObject oldObject,JSONObject newObject){


        Set<String> deleteKeySet = Sets.newHashSet(oldObject.keySet());
        Set<String> addKeySet = Sets.newHashSet(newObject.keySet());
        Set<String> modifyKeySet = Sets.newHashSet();

        for (String str : oldObject.keySet()){
            if (addKeySet.contains(str)){
                deleteKeySet.remove(str);
                addKeySet.remove(str);
                modifyKeySet.add(str);
            }
        }

        System.out.println(deleteKeySet);
        System.out.println(addKeySet);


        for (String string : modifyKeySet){
            if (oldObject.get(string) instanceof JSONObject && newObject.get(string) instanceof JSONObject){
                diff((JSONObject)oldObject.get(string),(JSONObject)newObject.get(string));
            } else if (!oldObject.get(string).equals(newObject.get(string))){
                System.out.println(oldObject.get(string));
                System.out.println(oldObject.get(string) + " change to "+newObject.get(string));
                break;
            }
        }

    }

    public static void arrayDiff(JSONArray oldObject, JSONArray newObject){

        List<Object> a = Lists.newArrayList(oldObject);
        List<Object> b = Lists.newArrayList(newObject);

        Iterator iteratorsA = a.iterator();
        Iterator iteratorsB = b.iterator();

        while (iteratorsA.hasNext()){
            JSONObject aObject = (JSONObject)iteratorsA.next();
            while (iteratorsB.hasNext()){
                JSONObject bObject = (JSONObject)iteratorsB.next();

            }
        }

    }


}