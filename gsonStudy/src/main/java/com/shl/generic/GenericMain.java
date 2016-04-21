package com.shl.generic;

import com.shl.generic.APIResult;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by jackson on 16/4/12.
 */
public class GenericMain {

    public static void main(String[] args) {

        APIResult<Person> apiResult = new APIResult<Person>();
        apiResult.setData(new Person("shl",25));

        APIResult<Person> innerNoClass = new APIResult<Person>(){};

        try {
            Field datafield = apiResult.getClass().getDeclaredField("data");
            System.out.println(datafield.getClass());

            Type mapRealType = datafield.getGenericType();

            if (mapRealType instanceof ParameterizedType){
                ParameterizedType parameterizedType = (ParameterizedType)mapRealType;
                System.out.println(parameterizedType);
            }

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        //getGenericSuperclass()获得带有泛型的父类
        //Type是 Java 编程语言中所有类型的公共高级接口。它们包括原始类型、参数化类型、数组类型、类型变量和基本类型。
        Type type =  (Type) innerNoClass.getClass().getGenericSuperclass();

        System.out.println(type);
        //ParameterizedType参数化类型，即泛型
        ParameterizedType p = (ParameterizedType)type;

        for (Type c : p.getActualTypeArguments()){
            System.out.println((Class)(c));
        }

        System.out.println("success!!");
    }
}
