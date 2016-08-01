package com.shl.study.hystrix;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by jackson on 16/8/1.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class HystrixTest {

    @Autowired
    private SayHelloService helloService;

    @Test
    public void test(){
        try {
            for (int i=0 ; i<100;i++){
                Thread.sleep(500);

                if (i%2 == 0)
                    helloService.sayHello("study");
                else if (i%3 == 0)
                    helloService.sayHello(null);
                else
                    helloService.sayHello("shl");
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
