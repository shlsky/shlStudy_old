package com.shl.easyhttp.support;

import com.shl.easyhttp.utils.ClassScanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.core.PriorityOrdered;

/**
 * Created by wenjli on 4/22/16.
 */
public class HttpComponentHandler implements BeanDefinitionRegistryPostProcessor, PriorityOrdered {

    private final static Logger logger = LoggerFactory.getLogger(HttpComponentHandler.class);

    private BeanDefinitionRegistry registry;

    private String scanPackages;

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        this.registry = registry;
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

        ClassScanner classScanner = new ClassScanner() {
            @Override
            public void onSuccess(BeanDefinition beanDefinition) {
                String beanClassName = beanDefinition.getBeanClassName();
                Class<?> aClass = null;
                try {
                    aClass = Class.forName(beanClassName);
                } catch (ClassNotFoundException ex) {
                    logger.error("class {} not found", beanClassName);
                }
                if (aClass == null) {
                    logger.info("class {} load fail", beanClassName);
                    return;
                }
                RootBeanDefinition rootBeanDefinition = new RootBeanDefinition(RetrofitFactoryBean.class);
                registry.registerBeanDefinition(beanClassName, rootBeanDefinition);
            }
        };

        classScanner.scan(scanPackages);

    }

    @Override
    public int getOrder() {
        return HIGHEST_PRECEDENCE;
    }
}
