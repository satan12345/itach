package com.atguigu;

import com.atguigu.bean.Person;
import com.atguigu.config.MainConfig;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainTest {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfig.class);
//        Person bean = context.getBean(Person.class);
//        System.out.println(bean);
//
//        BeanDefinition peroson = context.getBeanDefinition("Person");
//        System.out.println(peroson);
        String[] beanDefinitionNames = context.getBeanDefinitionNames();
        for (int i = 0; i < beanDefinitionNames.length; i++) {
            System.out.println(beanDefinitionNames[i]);
        }
//        System.out.println("----------------------");
//        String[] beanNamesForType = context.getBeanNamesForType(Person.class);
//        for (int i = 0; i < beanNamesForType.length; i++) {
//            System.out.println(beanNamesForType[i]);
//        }

    }
}
