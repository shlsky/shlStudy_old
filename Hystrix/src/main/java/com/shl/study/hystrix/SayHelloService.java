package com.shl.study.hystrix;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;


/**
 * Created by jackson on 16/8/1.
 */
@Service
public class SayHelloService {

    @HystrixCommand(groupKey = "service",commandKey="sayHello",fallbackMethod = "sayHelloFallBack",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "150000"),
                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "5"),
                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "10")
            })
    public String sayHello(String name) throws Exception{

        System.out.println("init init!");
        if (name == null)
            throw new RuntimeException("null");
        if ("shl".equals(name)){
            throw new Exception("haha");
        }
        System.out.println("hello "+name);
        System.out.println("---");
        return name;
    }

    public String sayHelloFallBack(String id,Throwable throwable){

        System.out.println("fallback! "+id);
        System.out.println(throwable);
        System.out.println("---");
        return null;
    }


}
