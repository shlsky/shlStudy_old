package com.shl.study.hystrix;


import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;

/**
 * Created by jackson on 16/8/2.
 */
public class SayHelloExtendService extends HystrixCommand<String> {

    private SayHelloAnnotationService sayHelloAnnotationService;
    private Throwable throwable;
    private String param;
    public SayHelloExtendService(String groupKey,String commandKey,SayHelloAnnotationService sayHelloAnnotationService,String param) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey(groupKey))
                .andCommandKey(HystrixCommandKey.Factory.asKey(commandKey))
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
                                .withExecutionTimeoutInMilliseconds(150000)
                                .withCircuitBreakerRequestVolumeThreshold(5)
                                .withCircuitBreakerErrorThresholdPercentage(10)
                ));
        this.sayHelloAnnotationService = sayHelloAnnotationService;
        this.param = param;
    }

    @Override
    protected String run() throws Exception {
        String res = null;
        try{
            res = sayHelloAnnotationService.sayBye(param);
        }catch (Exception e){
            this.throwable = e;
            throw e;
        }

        return res;
    }

    @Override
    protected String getFallback(){
        System.out.println("init fallback "+this.param);
        if (this.throwable != null){
            System.out.println(throwable.getLocalizedMessage());
        }
        System.out.println("--------");
        return "fallback";
    }

}
