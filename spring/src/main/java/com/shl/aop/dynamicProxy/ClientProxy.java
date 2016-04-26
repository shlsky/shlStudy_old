package com.shl.aop.dynamicProxy;

import org.springframework.aop.framework.ProxyFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Created by jackson on 16/4/6.
 */
public class ClientProxy implements FactoryBean<Object>
        ,ApplicationContextAware,InitializingBean {

    private SayhelloServiceImpl sayhelloService;

    private ApplicationContext applicationContext = null;

    private Class<?> serviceInterface = null;

    private Object serviceProxy = null;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public Object getObject() throws Exception {
        return serviceProxy;
    }

    @Override
    public Class<?> getObjectType() {
        return serviceInterface;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        ClientInvokeInterceptor clientInterceptor = new ClientInvokeInterceptor(applicationContext,SayhelloServiceImpl.class);
        ProxyFactory pf = new ProxyFactory(serviceInterface, clientInterceptor);
        serviceProxy = pf.getProxy();
    }

    public void destroy() {}

    public Class<?> getServiceInterface() {
        return serviceInterface;
    }

    public void setServiceInterface(Class<?> serviceInterface) {
        this.serviceInterface = serviceInterface;
    }

    public Object getServiceProxy() {
        return serviceProxy;
    }

    public void setServiceProxy(Object serviceProxy) {
        this.serviceProxy = serviceProxy;
    }

    public SayhelloServiceImpl getSayhelloService() {
        return sayhelloService;
    }

    public void setSayhelloService(SayhelloServiceImpl sayhelloService) {
        this.sayhelloService = sayhelloService;
    }
}
