package com.atguigu;

import com.atguigu.aop.MathCalculator;
import com.atguigu.config.MainConfig2;
import com.atguigu.config.MainConfigOfAOP;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AopTest {
    AnnotationConfigApplicationContext context;

    @Before
    public void init(){
        context=new AnnotationConfigApplicationContext(MainConfigOfAOP.class);
    }
    @After
    public void destory(){
        context.close();
    }

    @Test
    public void test1(){
        MathCalculator mathCalculator=context.getBean(MathCalculator.class);
        int result=mathCalculator.div(6,0);
    }
}
