package com.mars.others;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.googlecode.jmapper.JMapper;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by chengbinjiao on 16/10/24.
 */
public class TransEntity {

    Student student = null;

    @Before public void before(){
        System.out.println("init student");
        student = new Student();
        student.setName("zhangsan");
        student.setAge(10);
    }

    /**
     * 1.get\set
     * 优点:简单;执行效率高;复杂实体转换
     * 缺点:写代码麻烦,容易出错
     */
    @Test public void testGetSet(){
        System.out.println("----->start:"+System.currentTimeMillis());
        long start = System.currentTimeMillis();

        Teacher teacher = new Teacher();
        teacher.setName(student.getName());
        teacher.setAge(student.getAge());
        System.out.println("----->end:"+System.currentTimeMillis());

        System.out.println("耗时:"+(System.currentTimeMillis()-start));
        System.out.println(teacher.toString());
    }

    /**
     * 2.org.apache.commons.beanutils.BeanUtils.copyProperties
     * 优点:代码简单
     * 缺点:转换速度很慢,百毫秒,转换速度较PropertyUtils.copyProperties()稍快
     */
    @Test public void testCommonsBeanUtils(){
        System.out.println("----->start:"+System.currentTimeMillis());
        long start = System.currentTimeMillis();

        Teacher teacher = new Teacher();
        try {
            org.apache.commons.beanutils.BeanUtils.copyProperties(teacher, student);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        System.out.println("----->end:"+System.currentTimeMillis());

        System.out.println("耗时:"+(System.currentTimeMillis()-start));
        System.out.println(teacher.toString());
    }

    /**
     * 3.org.springframework.beans.BeanUtils.copyProperties
     * 优点:代码简单
     * 缺点:转换速度很慢,百毫秒,转换速度较PropertyUtils.copyProperties()稍快
     */
    @Test public void testStringBeanUtils(){
        System.out.println("----->start:"+System.currentTimeMillis());
        long start = System.currentTimeMillis();

        Teacher teacher = new Teacher();
        org.springframework.beans.BeanUtils.copyProperties(teacher, student);
        System.out.println("----->end:"+System.currentTimeMillis());

        System.out.println("耗时:"+(System.currentTimeMillis()-start));
        System.out.println(teacher.toString());
    }

    /**
     * 4.org.apache.commons.beanutils.PropertyUtils.copyProperties
     * 优点:代码简单
     * 缺点:转换速度很慢,百毫秒
     */
    @Test public void testPropertyUtils(){
        System.out.println("----->start:"+System.currentTimeMillis());
        long start = System.currentTimeMillis();

        Teacher teacher = new Teacher();
        try {
            org.apache.commons.beanutils.PropertyUtils.copyProperties(teacher, student);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        System.out.println("----->end:"+System.currentTimeMillis());

        System.out.println("耗时:"+(System.currentTimeMillis()-start));
        System.out.println(teacher.toString());
    }

    /**
     * 5.Fastjson
     * 优点:代码简单
     * 缺点:转换速度很慢,百毫秒
     */
    @Test public void testFastjson(){
        System.out.println("----->start:"+System.currentTimeMillis());
        long start = System.currentTimeMillis();

        String str = JSON.toJSONString(student);
        System.out.println(str);
        Teacher teacher = new Teacher();
        teacher = JSON.parseObject(str, new TypeReference<Teacher>(){});
        System.out.println("----->end:"+System.currentTimeMillis());

        System.out.println("耗时:"+(System.currentTimeMillis()-start));
        System.out.println(teacher.toString());
    }

    /**
     * 6.Gson
     * 优点:代码简单
     * 缺点:转换速度很慢,百毫秒
     */
    @Test public void testGson(){
        System.out.println("----->start:"+System.currentTimeMillis());
        long start = System.currentTimeMillis();

        Gson gson = new Gson();
        String str = gson.toJson(student);
        System.out.println(str);
        Teacher teacher = new Teacher();
        teacher = gson.fromJson(str, new TypeToken<Teacher>() {}.getType());
        System.out.println("----->end:"+System.currentTimeMillis());

        System.out.println("耗时:"+(System.currentTimeMillis()-start));
        System.out.println(teacher.toString());
    }

    /**
     * 7.用cglib的BeanCopier
     * 试过,如果不改源码会报错
     * 改源码参考:http://www.itmmd.com/201512/740.html
     * 效率没有验证,据说比上面的方法快
     */

    /**
     * 8.JMapper
     * 优点:代码简单
     * 缺点:转换速度很慢,百毫秒
     */
    @Test public void testJMapper(){
        System.out.println("----->start:"+System.currentTimeMillis());
        long start = System.currentTimeMillis();

        Teacher teacher = new Teacher();
        JMapper<Teacher, Student> mapper;
        mapper = new JMapper<Teacher, Student>(Teacher.class, Student.class, "jmapper.bean.xml/SimpleDestination.xml");
        System.out.println("----->end1:"+System.currentTimeMillis());
        teacher = mapper.getDestination(student);
        System.out.println("----->end2:"+System.currentTimeMillis());

        System.out.println("耗时:"+(System.currentTimeMillis()-start));
        System.out.println(teacher.toString());
    }
}
