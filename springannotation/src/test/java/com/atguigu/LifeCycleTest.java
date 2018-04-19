package com.atguigu;

import com.atguigu.config.MainConfigOfLifeCycle;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class LifeCycleTest {

    AnnotationConfigApplicationContext context;
    @Before
    public void init(){
        context=new AnnotationConfigApplicationContext(MainConfigOfLifeCycle.class);
    }
    @Test
    public void test1(){
        System.out.println("容器创建完成");
    }
    @After
    public void after(){
        context.destroy();
    }
}
