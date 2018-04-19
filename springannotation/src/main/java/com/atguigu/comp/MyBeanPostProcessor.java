package com.atguigu.comp;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;



@Component
public class MyBeanPostProcessor implements BeanPostProcessor,InitializingBean{

    public MyBeanPostProcessor(){
        System.out.println("MyBeanPostProcessor constructor");
    }


    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("postProcessBeforeInitialization:"+beanName+"==>"+bean);
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("postProcessAfterInitialization:"+beanName+"==>"+bean);
        return bean;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("pig afterPropertiesSet");

    }
}
