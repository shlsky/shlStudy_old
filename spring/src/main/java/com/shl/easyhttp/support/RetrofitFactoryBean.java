package com.shl.easyhttp.support;

import com.shl.easyhttp.annotations.HttpComponent;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.core.annotation.AnnotationUtils;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by sky on 4/22/16.
 */
public class RetrofitFactoryBean implements FactoryBean<Retrofit> {

    private Class<?> targetClass;

    public RetrofitFactoryBean(Class<?> targetClass) {
        this.targetClass = targetClass;
    }

    @Override
    public Retrofit getObject() throws Exception {
        HttpComponent annotation = AnnotationUtils.getAnnotation(targetClass, HttpComponent.class);
        Map<String, Object> annotationAttributes = AnnotationUtils.getAnnotationAttributes(annotation);
        Long connectTimeout = (Long) annotationAttributes.get("connectTimeout");
        Long readTimeout = (Long) annotationAttributes.get("readTimeout");
        String baseUrl = ((String) annotationAttributes.get("baseUrl")).trim();
        if (!StringUtils.endsWith(baseUrl, "/")) {
            baseUrl = baseUrl + "/";
        }
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(connectTimeout, TimeUnit.MILLISECONDS)
                .readTimeout(readTimeout, TimeUnit.MILLISECONDS).connectionPool(new ConnectionPool()).build();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(baseUrl).client(okHttpClient)
                .addConverterFactory(JacksonConverterFactory.create())
                .addConverterFactory(SimpleXmlConverterFactory.create()).build();
        return (Retrofit) retrofit.create(targetClass);
    }

    @Override
    public Class<?> getObjectType() {
        return targetClass;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
