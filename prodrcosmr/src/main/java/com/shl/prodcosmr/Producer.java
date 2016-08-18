package com.shl.prodcosmr;

import java.util.List;

/**
 * Created by jackson on 16/8/10.
 */
public abstract class Producer<T> implements Runnable {
    @Override
    public final void run() {

    }

    public abstract List<T> product();
}
