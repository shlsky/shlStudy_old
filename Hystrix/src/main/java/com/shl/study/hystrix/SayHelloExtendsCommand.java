package com.shl.study.hystrix;


import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;

/**
 * Created by jackson on 16/8/2.
 */
public class SayHelloExtendsCommand extends HystrixCommand<String> {

    private SayHelloService sayHelloService;
    private Throwable throwable;
    private String param;
    public SayHelloExtendsCommand(String groupKey, String commandKey, SayHelloService sayHelloService, String param) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey(groupKey))
                .andCommandKey(HystrixCommandKey.Factory.asKey(commandKey))
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
                                .withExecutionTimeoutInMilliseconds(150000)
                                .withCircuitBreakerRequestVolumeThreshold(5)
                                .withCircuitBreakerErrorThresholdPercentage(10)
                                .withMetricsRollingStatisticalWindowInMilliseconds(300000)//统计的时间窗口置为5分钟,默认是10s(所以上次执演示失败)
                ));
        this.sayHelloService = sayHelloService;
        this.param = param;
    }

    @Override
    protected String run() throws Exception {
        String res = null;
        try{
            res = sayHelloService.sayHello(param);
        }catch (Exception e){
            this.throwable = e;
            throw e;
        }

        return res;
    }

    @Override
    protected String getFallback(){
        System.out.println("invoke fallback "+this.param);
        if (this.throwable != null){
            System.out.println(throwable.getLocalizedMessage());
        }
        System.out.println("--------");
        return "fallback";
    }

}
