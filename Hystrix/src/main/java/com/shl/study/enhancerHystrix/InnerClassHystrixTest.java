package com.shl.study.enhancerHystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.util.HystrixRollingNumberEvent;

/**
 * Created by jackson on 16/10/13.
 */
public class InnerClassHystrixTest extends ClusterHystrixCommand<Integer> {



    public InnerClassHystrixTest(HystrixCommand.Setter setter){
        super(setter);
    }

    @Override
    public Integer run() throws Exception {
        System.out.println("run invoke");
        return Integer.MIN_VALUE;
    }

    @Override
    public boolean clusterHealth() throws Exception {
        System.out.println("clusterHandle");
        return true;
    }

    @Override
    public Integer getFallback(){
        System.out.println("getFallback invoke");
        return Integer.MAX_VALUE;
    }


    public static void main(String[] args) {
        HystrixCommand.Setter setter = HystrixCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("SHL1"))
                .andCommandKey(HystrixCommandKey.Factory.asKey("SHL1"))
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
                        .withExecutionTimeoutInMilliseconds(150000)
                        .withCircuitBreakerRequestVolumeThreshold(5)
                        .withCircuitBreakerErrorThresholdPercentage(60)
                        .withMetricsRollingStatisticalWindowInMilliseconds(300000)//统计的时间窗口置为5分钟,默认是10s(所以上次执演示失败)
                        .withMetricsHealthSnapshotIntervalInMilliseconds(1)//采样时间间隔
                );


        for (int i=0 ;i<10;i++){
            System.out.println("--------------");
            InnerClassHystrixTest hystrixTest = new InnerClassHystrixTest(setter);


            System.out.println(hystrixTest.execute());
            System.out.println(hystrixTest.getMetrics().getHealthCounts().getErrorCount());
            System.out.println(hystrixTest.getMetrics().getHealthCounts().getTotalRequests());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("--------------");
        }

    }
}
