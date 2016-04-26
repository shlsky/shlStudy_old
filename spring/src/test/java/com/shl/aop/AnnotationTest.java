package com.shl.aop;

import com.shl.aop.annotation.TestPoiAnnotatiomService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by jackson on 16/1/28.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class AnnotationTest {



    @Autowired
    private TestPoiAnnotatiomService testPoiAnnotatiomService;

    @Test
    public void test() {
        testPoiAnnotatiomService.syyhello();
    }

}
