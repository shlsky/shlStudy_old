package com.shl.aop;

import com.shl.aop.dynamicProxy.ISayhelloService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by jackson on 16/4/6.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:/applicationContext.xml"})
public class ClientProxyTest {

    @Autowired
    private ISayhelloService sayhelloService;

    @Test
    public void test(){
        sayhelloService.sayhell2one("shl");
    }
}
