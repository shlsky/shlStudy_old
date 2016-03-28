package com.shl.jmx.MXBeans;

/**
 * Created by jackson on 16/3/28.
 */
public interface QueueSamplerMXBean {
    public QueueSample getQueueSample();
    public void clearQueue();
}
