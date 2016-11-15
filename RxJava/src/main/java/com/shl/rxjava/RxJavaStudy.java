package com.shl.rxjava;


import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action1;
import rx.observables.BlockingObservable;
import rx.schedulers.Schedulers;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * Created by jackson on 16/8/19.
 */
public class RxJavaStudy {

    public static Observable<String> toObservable(){
        return Observable.from(new String[]{"1","2"});
    }

    public static void ObservableUse(final String name){
        Subscription subscription  = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                System.out.println("OnSubscribe.call is invoked !");
                subscriber.onNext(name);
            }

            //当Observable的subscribe方法被调用时,Observable.OnSubscribe会执行。
            //当subscribe()被调用时,OnSubscribe.call()被调用,call()的subscriber,就是subscribe()传入的subscriber
        }).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {
                System.out.println("onCompleted is invoked!");
            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onNext(String s) {
                System.out.println("onNext is invoked!");
            }
        });
    }

    public static void futureUse(){

        Observable<Long> values = Observable.create(new Observable.OnSubscribe<Long>(){

            @Override
            public void call(Subscriber<? super Long> subscriber) {
                try {
                    System.out.println("OnSubscribe call night!");
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                subscriber.onNext(69l);   //分析源码可知此处是设置返回的value
                subscriber.onCompleted(); //此处保证toFuture()方法中引入的CountDownLatch,被countDown()
            }
        });


        Future<Long> future1 = values.toBlocking().toFuture();
        try {
            System.out.println("future get()");
            System.out.println(future1.get(5000,TimeUnit.MILLISECONDS));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void liftUse(){
        Observable observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                System.out.println("OnSubscribe call currentThread:"+Thread.currentThread().getId());
                System.out.println("OnSubscribe.call is invoked !");
                subscriber.onNext("liftUse");
                subscriber.onCompleted();
            }
            //指定OnSubscribe的执行线程
        }).subscribeOn(Schedulers.newThread())

                //指定subscriber的执行线程,如果不指定,则根据线程不变原则,subscriber的执行线程与OnSubscribe是同一个。
                .observeOn(Schedulers.newThread());

        //lift通过Operator 装饰原subscriber生成新的subscriber(用的是装饰模式)
        observable = observable.lift(new Observable.Operator<String,String>(){

            @Override
            public Subscriber<? super String> call(final Subscriber<? super String> subscriber) {
                return new Subscriber<String>(subscriber) {
                    @Override
                    public void onCompleted() {
                        System.out.println("subscriber onCompleted currentThread:"+Thread.currentThread().getId());
                        System.out.println("then invoke real onCompleted");
                        subscriber.onCompleted();
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("then invoke real onError");
                        subscriber.onError(e);
                    }

                    @Override
                    public void onNext(String s) {
                        System.out.println("then invoke real onNext");
                        subscriber.onNext(s);
                    }
                };
            }
        });

        Future<String> future = observable.toBlocking().toFuture();
        try {
            System.out.println("future get()");
            System.out.println(future.get(5000,TimeUnit.MILLISECONDS));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public static void main(String[] args) {

        System.out.println("main currentThread:" + Thread.currentThread().getId());
        liftUse();

    }
}
