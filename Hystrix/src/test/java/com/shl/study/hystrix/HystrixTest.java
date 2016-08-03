package com.shl.study.hystrix;

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
    private SayHelloAnnotationService helloService;

    @Autowired
    private JavassistStudy javassistStudy;

    @Test
    public void test(){
//        BeanCopier copier = BeanCopier.create(helloService.getClass(),helloService.getClass(),false);
//
//        SayHelloAnnotationService helloService1 = null;
//        try {
//            helloService1 = helloService.getClass().newInstance();
//        } catch (InstantiationException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }
//
////        copier.copy(helloService,helloService1,null);
//
//        BeanUtils.copyProperties(helloService,helloService1);




        try {
            for (int i=0 ; i<100;i++){
                Thread.sleep(500);

                if (i%2 == 0)
                    helloService.sayHello("study");
                else if (i%3 == 0)
                    helloService.sayHello(null);
                else
                    helloService.sayHello("shl");
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void modifyAnnotation(){

        SayHelloAnnotationService helloService1 = null;

        try {
            for (int i=0;i<3;i++){
                helloService1 = javassistStudy.listBeanDefine();
            }

            for (int i=0 ; i<100;i++){
                Thread.sleep(500);


                helloService.sayHello("shl");
                helloService1.sayHello("study");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void extendtest(){

        try {
            ExecutorService executorService = Executors.newFixedThreadPool(6);

            for (int i=0;i<100;i++){



                if (i%4 == 0){
                    executorService.submit(new MultiThread(new SayHelloExtendService("SHL0","SHL0",helloService,"study")));
//                    SayHelloExtendService helloExtendService = new SayHelloExtendService("SHL0","SHL0",helloService,"study");
//                    helloExtendService.execute();
//                    System.out.println();
                }

                else if (i%3 == 0){
                    executorService.submit(new MultiThread(new SayHelloExtendService("SHL1","SHL1",helloService,null)));
//                    SayHelloExtendService helloExtendService = new SayHelloExtendService("SHL1","SHL1",helloService,null);
//                    helloExtendService.execute();
//                    System.out.println();
                }
//                else{
//                    SayHelloExtendService helloExtendService = new SayHelloExtendService("SHL","SHL",helloService,"shl");
//                    System.out.println(helloExtendService.execute());
//                }

            }
            Thread.sleep(10000);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private class MultiThread implements Callable<String>{

        private SayHelloExtendService helloExtendService;
        @Override
        public String call() throws Exception {
            return this.helloExtendService.execute();
        }

        public MultiThread(SayHelloExtendService helloExtendService){
            this.helloExtendService = helloExtendService;
        }
    }
}
