package com.shl.rxjava;


import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.observables.BlockingObservable;

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

    public static Observable<String> toObservable(final String name){
        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                System.out.println("OnSubscribe.call is invoked !");
                subscriber.onNext(name);
            }
        });
        return observable;
    }
    public static void main(String[] args) {
//        Observable<String> observable = toObservable("shl");
//        System.out.println("start subscribe !");
//        observable.subscribe(new Subscriber<String>() {
//            @Override
//            public void onCompleted() {
//                System.out.println("onCompleted is invoked!");
//            }
//
//            @Override
//            public void onError(Throwable throwable) {
//
//            }
//
//            @Override
//            public void onNext(String s) {
//                System.out.println("onNext is invoked!");
//            }
//        });
//
//        System.out.println("start future !");
//        BlockingObservable<String> blockingObservable = observable.toBlocking();
//        Future<String> future =  blockingObservable.toFuture();

        System.out.println("Blocking");
//        Observable<Long> values = Observable.timer(10000, TimeUnit.MILLISECONDS);

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
                subscriber.onCompleted();
            }
        });

        //当Observable的subscribe方法被调用时,Observable.OnSubscribe会执行。
        values.subscribe(new Action1<Long>() {
            @Override
            public void call(Long aLong) {
                System.out.println(aLong+4l);
                aLong = 4l;
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
}
