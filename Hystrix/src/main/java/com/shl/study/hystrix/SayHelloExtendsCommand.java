package com.shl.study.hystrix;


import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;

/**
 * Created by jackson on 16/8/2.
 */
public class SayHelloExtendsCommand extends HystrixCommand<Integer> {

    private SayHelloService sayHelloService;
    private Throwable throwable;
    private Integer param;
    public SayHelloExtendsCommand(HystrixCommand.Setter setter, SayHelloService sayHelloService, Integer param) {
        super(setter);
        this.sayHelloService = sayHelloService;
        this.param = param;
    }

    @Override
    protected Integer run() throws Exception {
        Integer res = null;
        try{
            res = sayHelloService.sayHello(param);
        }catch (Exception e){
            this.throwable = e;
            throw e;
        }

        return res;
    }

    @Override
    protected Integer getFallback(){
        System.out.println("invoke fallback "+this.param);
        if (this.throwable != null){
            System.out.println(throwable.getLocalizedMessage());
        }
        //如果fallback 抛出异常
//        boolean fallbackExptest = true;
//        if (fallbackExptest){
//            String aa = null;
//            System.out.println("--------"+aa.length());
//
//        }
        return param;
    }

}
