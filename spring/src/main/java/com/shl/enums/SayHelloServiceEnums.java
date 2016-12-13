package com.shl.enums;

import com.shl.aop.annotation.StatpoitestService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.EnumSet;

/**
 * Created by jackson on 2016/12/13.
 */
public enum  SayHelloServiceEnums implements ISayHelloService {

    SHL("SHL") {
        @Override
        public void sayHello() {
            this.statpoitestService.sysBye();
            System.out.println(this.name);
        }
    },
    ZZY("ZZY") {
        @Override
        public void sayHello() {
            System.out.println(this.name);
        }
    };
    @Component
    public static class ServiceInjector{
        @Autowired
        public StatpoitestService statpoitestService;

        @PostConstruct
        public void postConstruct() {
            for (SayHelloServiceEnums rt : EnumSet.allOf(SayHelloServiceEnums.class))
                rt.setStatpoitestService(statpoitestService);
        }
    }

    @Setter
    protected StatpoitestService statpoitestService;

    protected String name;

    private SayHelloServiceEnums(String name){
        this.name = name;
    }



}
