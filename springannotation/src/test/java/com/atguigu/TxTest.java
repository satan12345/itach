package com.atguigu;

import com.atguigu.tx.TxConfig;
import com.atguigu.tx.UserService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TxTest {
    AnnotationConfigApplicationContext context;

    @Before
    public void init(){
        context=new AnnotationConfigApplicationContext(TxConfig.class);
    }
    @After
    public void destory(){
        context.close();
    }




    @Test
    public void test1(){
        UserService user = context.getBean(UserService.class);
        user.insetUser();

    }
}
