package com.shl.easyhttp.utils;

import com.google.common.collect.Sets;

import com.shl.easyhttp.annotations.HttpComponent;
import org.springframework.beans.factory.BeanDefinitionStoreException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ScannedGenericBeanDefinition;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.Set;

/**
 * Created by sky on 4/21/16.
 */
public abstract class ClassScanner {

    private final String CLASSPATH_ALL_URL_PREFIX = "classpath*:";

    private final String DEFAULT_RESOURCE_PATTERN = "**/*.class";

    private final ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();

    private final MetadataReaderFactory metadataReaderFactory = new CachingMetadataReaderFactory(
            resourcePatternResolver);

    public Set<BeanDefinition> scan(String packageNames) {

        String[] packageArray = StringUtils
                .tokenizeToStringArray(packageNames.trim(), ConfigurableApplicationContext.CONFIG_LOCATION_DELIMITERS);

        Set<BeanDefinition> definitionSet = Sets.newHashSet();

        for (String packageName : packageArray) {
            String packageSearchPath =
                    CLASSPATH_ALL_URL_PREFIX + ClassUtils.convertClassNameToResourcePath(packageName) + "/"
                            + DEFAULT_RESOURCE_PATTERN;

            Resource[] resources;
            try {
                resources = resourcePatternResolver.getResources(packageSearchPath);
            } catch (IOException ex) {
                throw new RuntimeException("getResource exception");
            }

            for (Resource resource : resources) {
                if (resource.isReadable()) {
                    try {
                        MetadataReader metadataReader = metadataReaderFactory.getMetadataReader(resource);
                        AnnotationMetadata annotationMetadata = metadataReader.getAnnotationMetadata();
                        Set<String> annotationTypes = annotationMetadata.getAnnotationTypes();
                        if (annotationTypes.contains(HttpComponent.class.getName())) {
                            ScannedGenericBeanDefinition sbd = new ScannedGenericBeanDefinition(metadataReader);
                            sbd.setResource(resource);
                            sbd.setSource(resource);
                            onSuccess(sbd);
                            definitionSet.add(sbd);
                        }
                    } catch (Throwable ex) {
                        throw new BeanDefinitionStoreException(
                                "Failed to read candidate component class: " + resource, ex);
                    }
                }
            }
        }
        return definitionSet;
    }

    public abstract void onSuccess(BeanDefinition beanDefinition);

}
