package com.shl.springCustomTags.springNSHandler;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * Created by jackson on 16/4/23.
 */
public class ShlConfigNSHandler extends NamespaceHandlerSupport {
    @Override
    public void init() {
        registerBeanDefinitionParser("shlConfig", new ShlConfigTagsBeanDfParser());
    }
}
