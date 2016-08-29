package com.shl.shutdownhook;

/**
 * Created by jackson on 16/8/26.
 */
public class ShutdownHook {

    public static void main(String[] args) {



        Thread runnable = new Thread() {
            @Override
            public void run() {
                Runtime.getRuntime().addShutdownHook(new Thread(){
                    @Override
                    public void run(){
                        System.out.println("shutdownHook is running...");
                    }
                });
            }
        };
        runnable.start();


        while (true){
            try {
                System.out.println("main running...");
                Thread.sleep(3000);
            }catch (Exception e){
                e.printStackTrace();
            }
        }


    }

}
