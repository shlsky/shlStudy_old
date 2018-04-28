/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.shl.json;

/**
 * @author fufeng
 * @version $Id: Person.java, v 0.1 2017-09-12 上午10:55 fufeng Exp $$
 */
public class Person {

    /**
     * 姓名
     */
    private String name;

    /**
     * 年龄
     */
    private String age;

    public Person(String name, String age){
        this.name = name;
        this.age = age;
    }

    public Person getSelf(){
        return this;
    }

    /**
     * Getter method for property name.
     *
     * @return property value of name
     */
    public String getName() {
        return name;
    }

    /**
     * Setter method for property name.
     *
     * @param name value to be assigned to property name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter method for property age.
     *
     * @return property value of age
     */
    public String getAge() {
        return age;
    }

    /**
     * Setter method for property age.
     *
     * @param age value to be assigned to property age
     */
    public void setAge(String age) {
        this.age = age;
    }
}