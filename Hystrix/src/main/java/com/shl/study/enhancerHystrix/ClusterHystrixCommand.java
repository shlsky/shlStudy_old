package com.shl.study.enhancerHystrix;

import com.netflix.hystrix.HystrixCircuitBreaker;
import com.netflix.hystrix.HystrixCommand;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by jackson on 16/10/13.
 */
public abstract class ClusterHystrixCommand<R> extends HystrixCommand<R> {

    private Setter setter;

    private Enhancer enhancer = new Enhancer();

    public ClusterHystrixCommand(Setter setter) {
        super(setter);
        this.setter = setter;
    }

    @Override
    public abstract R run() throws Exception;

    //
    public abstract boolean clusterHealth() throws Exception;

    @Override
    public abstract R getFallback();

    @Override
    public final R execute() {

        return new HystrixCommand<R>(setter) {

            @Override
            protected R run() throws Exception {

                this.getMetrics();


                System.out.println("HystrixCommand.run");
                final Field this0 = this.getClass().getDeclaredField("this$0");
                this0.setAccessible(true);
                ClusterHystrixCommand<Integer> object = (ClusterHystrixCommand) this0.get(this);
                Method method = object.getClass().getMethod("run");

                //先判断是否是健康检查,如果是则直接调用方法
                if (this.circuitBreaker instanceof HystrixCircuitBreaker.HystrixCircuitBreakerImpl &&
                        ((HystrixCircuitBreaker.HystrixCircuitBreakerImpl) this.circuitBreaker).allowSingleTest()) {
                    return (R) method.invoke(object, null);
                }

                if (!clusterHealth())
                    throw new Exception("cluster isn't health");
                else {
                    System.out.println("1");
                }{
                    System.out.println("2");
                }
                return (R) method.invoke(object, null);

            }

            @Override
            protected R getFallback() {

                try {
                    final Field this0 = this.getClass().getDeclaredField("this$0");
                    this0.setAccessible(true);
                    ClusterHystrixCommand<Integer> object = (ClusterHystrixCommand) this0.get(this);
                    Method method = object.getClass().getMethod("getFallback");
                    return (R) method.invoke(object, null);
                } catch (Exception e) {
                    System.out.println(e);
                }


                return null;
            }

        }.execute();
    }

}
