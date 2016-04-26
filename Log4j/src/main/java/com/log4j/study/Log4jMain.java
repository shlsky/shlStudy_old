package com.log4j.study;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by jackson on 16/4/26.
 */
public class Log4jMain {
    /**
     * root logger
     */
    private static final Logger LOG = LoggerFactory.getLogger(Log4jMain.class);

    /**
     * 自定义model1 logger
     */
    private static final Logger MODEL_LOG1 = LoggerFactory.getLogger("model1");

    public static void printLog(int i) {
        LOG.info("log something....." + i);
    }

    public static void printModelLog1(int i) {
        MODEL_LOG1.info("log info model1 something....." + i);
        MODEL_LOG1.debug("log debug model1 something....." + i);
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i=10; i < 20; i++) {
            printLog(i);
            printModelLog1(i);
            Thread.sleep(1000);
        }
    }

}
