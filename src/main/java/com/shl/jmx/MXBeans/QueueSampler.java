package com.shl.jmx.MXBeans;

import java.util.Date;
import java.util.Queue;

/**
 * Created by jackson on 16/3/28.
 */
public class QueueSampler
        implements QueueSamplerMXBean {

    private Queue<String> queue;

    public QueueSampler (Queue<String> queue) {
        this.queue = queue;
    }

    public QueueSample getQueueSample() {
        synchronized (queue) {
            return new QueueSample(new Date(),
                    queue.size(), queue.peek());
        }
    }

    public void clearQueue() {
        synchronized (queue) {
            queue.clear();
        }
    }
}
