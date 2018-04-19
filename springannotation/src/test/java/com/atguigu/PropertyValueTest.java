package com.atguigu;

import com.atguigu.bean.Boss;
import com.atguigu.bean.Person;
import com.atguigu.config.MainConfigOfPropertyValue;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

import java.util.Arrays;

public class PropertyValueTest {

    AnnotationConfigApplicationContext context;

    @Test
    public void test1(){
        System.out.println("容器创建完成");
        String[] beanNames=context.getBeanDefinitionNames();
        Arrays.asList(beanNames).forEach(System.out::println);
        Person person=context.getBean(Person.class);
        System.out.println(person);
        ConfigurableEnvironment configurableEnvironment=context.getEnvironment();
        String property=configurableEnvironment.getProperty("person.skill");
        System.out.println(property);
    }
    @Before
    public void init(){
        context=new AnnotationConfigApplicationContext(MainConfigOfPropertyValue.class);
    }
    @After
    public void after(){
        context.destroy();
    }

    @Test
    public void test2(){
        Boss boss=context.getBean(Boss.class);
        System.out.println(boss);
    }
}
