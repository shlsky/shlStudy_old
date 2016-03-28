package com.shl.jmx.MBeans;

/**
 * Created by jackson on 16/3/28.
 */
public interface HelloMBean {

    void sayHello();
    int add(int x, int y);

    String getName();

    int getCacheSize();
    void setCacheSize(int size);
}
