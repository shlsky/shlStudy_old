package com.shl.prodcosmr;

import java.util.concurrent.BlockingQueue;

/**
 * Created by jackson on 16/8/10.
 */
public class ProdCosmr<T> {

    private Config config;
    private Producer<T> producer;
    private Consumer<T> consumer;
    private BlockingQueue<T> queue;

    public ProdCosmr(Config config, Producer<T> producer, Consumer<T> consumer){

    }

    public void execute(){

    }
    public static class Config{
        private Integer producerTheadCount;
        private Integer consumerThreadCount;
        private Integer queueLenth;

        public Config(Integer producerTheadCount,Integer consumerThreadCount,Integer queueLenth){

        }
    }
}
