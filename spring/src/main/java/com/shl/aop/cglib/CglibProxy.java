package com.shl.aop.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by jackson on 16/2/19.
 */
public class CglibProxy  implements MethodInterceptor{

    private Enhancer enhancer = new Enhancer();
    public Object getProxy(Class clazz){
        //设置需要创建子类的基类,基类非final,final类和方法是不能被重写的.
        enhancer.setSuperclass(clazz);

        //设置实现MethodInterceptor接口的类的实例,本例中就是this,
        enhancer.setCallback(this);

        //通过字节码技术动态创建子类实例
        return enhancer.create();
    }
    //实现MethodInterceptor接口方法
    public Object intercept(Object obj, Method method, Object[] args,
                            MethodProxy proxy) throws Throwable {
        Object result = null;
        if (method.getName().equals("say")){

            // proxy.invokeSuper()
            System.out.println("前置代理");
            Class classObj = obj.getClass().getSuperclass();
            Method method1 = classObj.getMethod("say1");
            method1.invoke(obj);

            //通过代理类调用父类中的方法
            result = proxy.invokeSuper(obj, args);
            System.out.println("后置代理");
        }else {
            //通过代理类调用父类中的方法
            result = proxy.invokeSuper(obj, args);
        }

        return result;
    }
}
