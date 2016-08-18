package com.shl.study.hystrix;

import org.springframework.stereotype.Service;

/**
 * Created by jackson on 16/8/18.
 */
@Service
public class SayHelloService {

    public String sayHello(String name) throws Exception{

        System.out.println("sayHello is invoked!");
        if (name == null)
            throw new NullPointerException();
        System.out.println("bye bye "+name);
        System.out.println("---");
        return name;
    }
}
