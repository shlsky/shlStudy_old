package com.shl.easyhttp.support;

import com.shl.easyhttp.common.HttpComponentConfigConstants;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;

/**
 * Created by wenjli on 4/22/16.
 */
public class HttpComponentConfigTagParser implements BeanDefinitionParser {
    @Override
    public BeanDefinition parse(Element element, ParserContext parserContext) {
        String scanPackages = element.getAttribute(HttpComponentConfigConstants.SCAN_PACKAGE_ATTRIBUTE_NAME);
        RootBeanDefinition rootBeanDefinition = new RootBeanDefinition(HttpComponentHandler.class);
        rootBeanDefinition.setScope(BeanDefinition.SCOPE_SINGLETON);
        MutablePropertyValues propertyValues = rootBeanDefinition.getPropertyValues();
        propertyValues.add(HttpComponentConfigConstants.SCAN_PACKAGE_ATTRIBUTE_NAME, scanPackages);
        parserContext.getRegistry().registerBeanDefinition(rootBeanDefinition.getBeanClassName(), rootBeanDefinition);
        return rootBeanDefinition;
    }
}
