package com.shl.aop.annotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by jackson on 16/1/28.
 */
@Service(value = "testPoiAnnotatiomService")
public class TestPoiAnnotatiomService {
    @Autowired
    private StatpoitestService statpoitestService;

    @AspectAnnotation
    public String syyhello(){
        statpoitestService.syshello();
        System.out.println("hello 3");
        return "hello 4";
    }
}
