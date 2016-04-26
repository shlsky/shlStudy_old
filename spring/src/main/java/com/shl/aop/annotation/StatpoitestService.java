package com.shl.aop.annotation;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

/**
 * Created by jackson on 16/1/28.
 */
@Service
public class StatpoitestService implements ApplicationContextAware {

    private ApplicationContext applicationContext;
    @AspectAnnotation
    public String syshello(){
        System.out.println("hello 1");
        applicationContext.getBean(this.getClass()).sysBye();
        return "hello 2";
    }

    @AspectAnnotation
    public String sysBye(){
        System.out.println("bye bye 1");
        return "bye bye 2";
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
