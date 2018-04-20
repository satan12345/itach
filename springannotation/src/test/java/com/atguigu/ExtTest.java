package com.atguigu;

import com.atguigu.ext.ExtConfig;
import com.atguigu.tx.TxConfig;
import com.atguigu.tx.UserService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ExtTest {
    AnnotationConfigApplicationContext context;

    @Before
    public void init(){
        context=new AnnotationConfigApplicationContext(ExtConfig.class);
    }
    @After
    public void destory(){
        context.close();
    }




    @Test
    public void test1(){
        context.publishEvent(new ApplicationEvent("我发布了一个事件") {

        });

    }
}
