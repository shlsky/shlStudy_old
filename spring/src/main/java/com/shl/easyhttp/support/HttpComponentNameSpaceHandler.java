package com.shl.easyhttp.support;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * Created by wenjli on 4/22/16.
 */
public class HttpComponentNameSpaceHandler extends NamespaceHandlerSupport {
    @Override
    public void init() {
        registerBeanDefinitionParser("httpComponent", new HttpComponentConfigTagParser());
    }
}
