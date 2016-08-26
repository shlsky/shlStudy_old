package com.shl;

import sun.misc.Signal;
import sun.misc.SignalHandler;

/**
 * Created by jackson on 16/8/26.
 */
public class CatchJVMQuitSignal implements SignalHandler{

    //kill pid---可以执行到该方法
    @Override
    public void handle(Signal signal) {
        System.out.println("catch the signal!");
        Runtime.getRuntime().exit(0);
    }

    //这个例子说明,idea终止运行发送的不是SIGTERM
    public static void main(String[] args) {
        Runtime.getRuntime().addShutdownHook(new Thread(){
            @Override
            public void run(){
                System.out.println("shutdownHook is running...");

            }
        });

        Signal signal = new Signal("TERM");
        Signal.handle(signal,new CatchJVMQuitSignal());


        while (true){
            try {
                System.out.println("main running...");
                Thread.sleep(2000);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
