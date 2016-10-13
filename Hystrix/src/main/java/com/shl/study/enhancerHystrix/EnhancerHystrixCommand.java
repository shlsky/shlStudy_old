package com.shl.study.enhancerHystrix;

import com.netflix.hystrix.HystrixCommand;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by jackson on 16/10/13.
 */
public abstract class EnhancerHystrixCommand<R> extends HystrixCommand<R> implements MethodInterceptor {

    private HystrixCommand.Setter setter;

    private Enhancer enhancer = new Enhancer();
    public EnhancerHystrixCommand(HystrixCommand.Setter setter){
        super(setter);
        this.setter = setter;
    }

    @Override
    public abstract R run() throws Exception;


    @Override
    public final Object intercept(Object obj, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        Object result = null;
        if (method.getName().equals("run")){

            Class classObj = obj.getClass().getSuperclass();
            Method method1 = classObj.getMethod("clusterHandle");
            method1.invoke(obj);

            //通过代理类调用父类中的方法
            result = methodProxy.invokeSuper(obj, args);
            System.out.println("后置代理");
        }else {
            //通过代理类调用父类中的方法
            result = methodProxy.invokeSuper(obj, args);
        }

        return result;
    }
    public EnhancerHystrixCommand getProxy(){

        //设置需要创建子类的基类,基类非final,final类和方法是不能被重写的.
        enhancer.setSuperclass(this.getClass());

        //设置实现MethodInterceptor接口的类的实例,本例中就是this,
        enhancer.setCallback(this);

        //通过字节码技术动态创建子类实例
        return (EnhancerHystrixCommand)enhancer.create();
    }

    public abstract void clusterHandle() throws Exception;

    @Override
    public final R execute(){

        return new HystrixCommand<R>(setter){
            @Override
            protected R run() throws Exception {
                final Field this0 = this.getClass().getDeclaredField("this$0");
                this0.setAccessible(true);
                EnhancerHystrixCommand<Integer> object = (EnhancerHystrixCommand)this0.get(this);
                Method method = object.getClass().getMethod("run");
                clusterHandle();
                return (R) method.invoke(object,null);
            }
        }.execute();
    }

}
