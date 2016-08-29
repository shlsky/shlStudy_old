package com.shl.shutdownhook;

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

        Signal signalTerm = new Signal("TERM");
        Signal.handle(signalTerm,new CatchJVMQuitSignal());

        Signal signalUSR2 = new Signal("USR2");
        Signal.handle(signalUSR2,new CatchJVMQuitSignal());

        /**
         * Exception in thread "main" java.lang.IllegalArgumentException: Signal already used by VM or OS: SIGKILL
         at sun.misc.Signal.handle(Signal.java:166)
         at com.shl.shutdownhook.CatchJVMQuitSignal.main(CatchJVMQuitSignal.java:35)

         Signal signalKILL = new Signal("KILL");
         Signal.handle(signalKILL,new CatchJVMQuitSignal());
         */



        while (true){
            try {
                System.out.println("main running...");
                Thread.sleep(2000);
                System.exit(0);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
