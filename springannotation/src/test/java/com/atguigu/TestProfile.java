package com.atguigu;

import com.atguigu.config.MainConfigOfProfile;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.sql.DataSource;

public class TestProfile {

    AnnotationConfigApplicationContext context;

    @Before
    public void init(){
        //创建一个applicationcontext
        context=new AnnotationConfigApplicationContext();
        //设置激活的profile
        context.getEnvironment().setActiveProfiles("prod");
        //加载配置类
        context.register(MainConfigOfProfile.class);
        // 刷新启动容器
        context.refresh();
    }
    @Test
    public void test1(){
        String[] beanNames=context.getBeanNamesForType(DataSource.class);
        System.out.println(beanNames.length);
        for (int i = 0; i < beanNames.length; i++) {
            System.out.println(beanNames[i]);
        }
    }
    @After
    public void after(){
        context.destroy();
    }
}
