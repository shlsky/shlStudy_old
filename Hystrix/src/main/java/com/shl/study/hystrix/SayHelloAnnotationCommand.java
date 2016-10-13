package com.shl.study.hystrix;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;


/**
 * Created by jackson on 16/8/1.
 */
@Service
public class SayHelloAnnotationCommand {

    @Autowired
    private SayHelloService sayHelloService;

    @HystrixCommand(groupKey = "service",commandKey="sayHello",fallbackMethod = "sayHelloFallBack",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "150000"), //超时时间150s
                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "5"),//最少请求数
                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "10")//判断熔断阈值
            })
    public Integer sayHello(String name) throws Exception{

        return sayHelloService.sayHello(1);
    }

    public String sayHelloFallBack(String id,Throwable throwable){

        System.out.println("fallback is invoked! "+id);
        System.out.println(throwable);
        System.out.println("---");
        return null;
    }
}
