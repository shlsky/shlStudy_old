package com.shl.aop;

import com.shl.enums.SayHelloServiceEnums;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by jackson on 2016/12/13.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:/applicationContext.xml"})
public class EnumAutowireTest {
    @Test
    public void test(){
        SayHelloServiceEnums.SHL.sayHello();
    }
}
