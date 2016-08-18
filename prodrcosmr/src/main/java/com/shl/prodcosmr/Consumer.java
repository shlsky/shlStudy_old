package com.shl.prodcosmr;

import java.util.Map;

/**
 * Created by jackson on 16/8/10.
 */
public abstract class Consumer<T> implements Runnable {

    @Override
    public final void run() {

    }
    //消费者抽象处理方法
    public abstract boolean consume(T tObject);

}
