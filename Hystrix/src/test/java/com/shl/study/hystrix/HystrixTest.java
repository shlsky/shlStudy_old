package com.shl.study.hystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by jackson on 16/8/1.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class HystrixTest {

    @Autowired
    private SayHelloService sayHelloService;

    @Autowired
    private JavassistStudy javassistStudy;

    @Test
    public void test(){
//        BeanCopier copier = BeanCopier.create(sayHelloService.getClass(),sayHelloService.getClass(),false);
//
//        SayHelloAnnotationService sayHelloService1 = null;
//        try {
//            sayHelloService1 = sayHelloService.getClass().newInstance();
//        } catch (InstantiationException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }
//
////        copier.copy(sayHelloService,sayHelloService1,null);
//
//        BeanUtils.copyProperties(sayHelloService,sayHelloService1);




        try {
            sayHelloService.sayHello(null);
            sayHelloService.sayHello(null);
            sayHelloService.sayHello(null);
            sayHelloService.sayHello(null);
            for (int i=0 ; i<30;i++){
                Thread.sleep(500);

                if (i%4 == 0)
                    sayHelloService.sayHello(i);
                else
                    sayHelloService.sayHello(i);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void modifyAnnotation(){

        SayHelloAnnotationCommand sayHelloService1 = null;

        try {
            for (int i=0;i<3;i++){
                sayHelloService1 = javassistStudy.listBeanDefine();
            }

            for (int i=0 ; i<100;i++){
                Thread.sleep(10);


                sayHelloService.sayHello(1);
                sayHelloService1.sayHello("study");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void extendtest(){
        HystrixCommand.Setter setter = HystrixCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("SHL1"))
                .andCommandKey(HystrixCommandKey.Factory.asKey("SHL1"))
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
                        .withExecutionTimeoutInMilliseconds(150000)
                        .withCircuitBreakerRequestVolumeThreshold(5)
                        .withCircuitBreakerErrorThresholdPercentage(10)
                        .withMetricsRollingStatisticalWindowInMilliseconds(300000)//统计的时间窗口置为5分钟,默认是10s(所以上次执演示失败)
                        .withMetricsHealthSnapshotIntervalInMilliseconds(1)//采样时间间隔
                        .withCircuitBreakerSleepWindowInMilliseconds(120000)
                );

        try {
            ExecutorService executorService = Executors.newFixedThreadPool(6);

            for (int i=0;i<100;i++){

                SayHelloExtendsCommand helloExtendService = new SayHelloExtendsCommand(setter,sayHelloService,i);
                Integer res = helloExtendService.execute();
                Thread.sleep(100);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private class MultiThread implements Callable<Integer>{

        private SayHelloExtendsCommand helloExtendService;
        @Override
        public Integer call() throws Exception {
            return this.helloExtendService.execute();
        }

        public MultiThread(SayHelloExtendsCommand helloExtendService){
            this.helloExtendService = helloExtendService;
        }
    }
}
