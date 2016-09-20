package com.shl.study.hystrix;

import org.springframework.stereotype.Service;

/**
 * Created by jackson on 16/8/18.
 */
@Service
public class SayHelloService {

    public Integer sayHello(Integer id) throws Exception{

        System.out.println("sayHello is invoked!");
        if (id%4 != 0)
            throw new NullPointerException();
        System.out.println("bye bye "+id%4);
        System.out.println("---");
        return id;
    }
}
