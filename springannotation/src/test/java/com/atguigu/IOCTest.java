package com.atguigu;

import com.atguigu.config.MainConfig2;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class IOCTest {
    AnnotationConfigApplicationContext context;

    @Before
    public void init(){
        context=new AnnotationConfigApplicationContext(MainConfig2.class);
    }
    @After
    public void destory(){
        context.close();
    }



    @Test
    public void test2(){
//        ConfigurableEnvironment configurableEnvironment=context.getEnvironment();
//        Map<String, Object>map= configurableEnvironment.getSystemProperties();
//        System.out.println(map);
        String[] beanDefinitionNames= context.getBeanDefinitionNames();
        for (int i = 0; i < beanDefinitionNames.length; i++) {
            System.out.println(beanDefinitionNames[i]);
        }
        Object colorFactoryBean=context.getBean("colorFactoryBean");
        Object beanFactory=context.getBean("&colorFactoryBean");
        System.out.println(beanFactory);
        System.out.println("类型为:"+colorFactoryBean.getClass());

    }

    @Test
    public void test1(){

//        String[] beanDefinitionNames = context.getBeanDefinitionNames();
//        for (int i = 0; i < beanDefinitionNames.length; i++) {
//            System.out.println(beanDefinitionNames[i]);
//        }
        System.out.println("1111");


        Object obj1= context.getBean("person");
        Object obj2= context.getBean("person");
        System.out.println(obj1);
        System.out.println(obj2);
        System.out.println(obj1 == obj2);
        context.close();

    }
}
