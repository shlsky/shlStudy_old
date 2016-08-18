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
                    sayHelloService.sayHello(null);
                else
                    sayHelloService.sayHello("study");
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
                Thread.sleep(500);


                sayHelloService.sayHello("shl");
                sayHelloService1.sayHello("study");
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

                Thread.sleep(10);

                if (i%4 == 0){
//                    executorService.submit(new MultiThread(new SayHelloExtendsCommand("SHL0","SHL0",sayHelloService,"study")));
//                    SayHelloExtendsCommand helloExtendService = new SayHelloExtendsCommand("SHL0","SHL0",sayHelloService,"study");
//                    String res = helloExtendService.execute();
//                    System.out.println();
                }

                else if (i%3 == 0){
//                    executorService.submit(new MultiThread(new SayHelloExtendsCommand("SHL1","SHL1",sayHelloService,null)));
                    SayHelloExtendsCommand helloExtendService = new SayHelloExtendsCommand("SHL1","SHL1",sayHelloService,null);
                    String res = helloExtendService.execute();
//                    System.out.println();
                }
//                else{
//                    SayHelloExtendsCommand helloExtendService = new SayHelloExtendsCommand("SHL","SHL",sayHelloService,"shl");
//                    System.out.println(helloExtendService.execute());
//                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private class MultiThread implements Callable<String>{

        private SayHelloExtendsCommand helloExtendService;
        @Override
        public String call() throws Exception {
            return this.helloExtendService.execute();
        }

        public MultiThread(SayHelloExtendsCommand helloExtendService){
            this.helloExtendService = helloExtendService;
        }
    }
}
