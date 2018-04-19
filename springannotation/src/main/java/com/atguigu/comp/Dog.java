package com.atguigu.comp;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.stereotype.Component;
import org.springframework.util.StringValueResolver;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
@Component
public class Dog implements ApplicationContextAware{

    ApplicationContext applicationContext;

    public Dog(){
        System.out.println("dog constructor");
    }
    @PostConstruct
    public void init(){
        System.out.println("dog init");
    }
    @PreDestroy
    public  void destort(){
        System.out.println("dog destory");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext=applicationContext;
    }


}
