package com.shl.study.hystrix;

import javassist.*;
import javassist.bytecode.AnnotationsAttribute;
import javassist.bytecode.ConstPool;
import javassist.bytecode.MethodInfo;
import javassist.bytecode.annotation.Annotation;
import javassist.bytecode.annotation.StringMemberValue;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

/**
 * Created by jackson on 16/8/2.
 */
@Service
public class JavassistStudy implements ApplicationContextAware {

    ApplicationContext applicationContext = null;

    public SayHelloAnnotationCommand listBeanDefine() throws NotFoundException{
        ClassPool classPool = ClassPool.getDefault();
        CtClass ct = classPool.get(SayHelloAnnotationCommand.class.getName());
        CtMethod cms = ct.getDeclaredMethod("sayHello");

        MethodInfo methodInfo = cms.getMethodInfo();

        ConstPool constPool = methodInfo.getConstPool();

        AnnotationsAttribute attribute1 = (AnnotationsAttribute) methodInfo.getAttribute(AnnotationsAttribute.visibleTag);
        //获取注解
        Annotation annotation1 = attribute1.getAnnotation("com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand");
        annotation1.addMemberValue("groupKey",new StringMemberValue("test", constPool));
        annotation1.addMemberValue("commandKey", new StringMemberValue("test", constPool));
        attribute1.setAnnotation(annotation1);

        /**新增注解信息
        AnnotationsAttribute attribute = new AnnotationsAttribute(constPool, AnnotationsAttribute.visibleTag);
        Annotation annotation = new Annotation("com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand", constPool);
        annotation.addMemberValue("groupKey", new StringMemberValue("hahah", constPool));
        annotation.addMemberValue("commandKey", new StringMemberValue("hahah", constPool));
        attribute.setAnnotation(annotation);
        methodInfo.addAttribute(attribute);
        */
        Object object = null;
        try {
            ct.writeFile();
            ct.defrost();
            object =  applicationContext.getAutowireCapableBeanFactory().createBean(SayHelloAnnotationCommand.class);


        } catch (Exception e) {
            e.printStackTrace();
        }

        return (SayHelloAnnotationCommand)object;
    }
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

        this.applicationContext = applicationContext;
    }
}
