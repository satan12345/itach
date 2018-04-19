package com.atguigu.config;

import com.atguigu.comp.MyBeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * bean的生命周期:
 *  bean创建--初始化--销毁的过程
 *  构造：(对象创建)
 *     单实例：在容器启动的时候创建对象
 *     多实例：在每次获取的时候创建
 *  BeanPostProcessor.postProcessBeforeInitialization
 *  初始化:对象创建完成 并赋值好 调用初始化方法
 *  BeanPostProcessor.postProcessAfterInitialization
 *  销毁：
 *      单实例：容器关闭的时候销毁
 *      多实例：容器不会管理这个Bean,所以容器不会调用销毁方式
 *  容器管理bean的生命周期
 *  我们可以自定义初始化与销毁方法
 *  容器在bean进行到当前生命周期的时候来调用我们自定义的初始化与销毁
 *  1. 指定初始化与销毁方法
 *      通过@Bean注解指定initMethod与destroyMethod
 *  2 通过Bean实现InitializingBean(定义初始化逻辑)
 *               DisPosableBean(定义销毁)
 *  3 可以使用JSR250
 *    @PostConstruct: 在bean创建完成并且赋值完成
 *    @PreDestroy 在容器销毁bean之前 通知我们进行清理工作
 * 4BeanPostProcessor:bean的后置处理器
 * 在Bean的初始化前后进行一些处理工作
 *      postProcessBeforeInitialization:初始化之前进行一些处理工作
 *      postProcessAfterInitialization:初始化之后进行一些处理工作
 *
 * Spring底层对BeanPostProcessor的使用
 *
 */
@ComponentScan(basePackages = {"com.atguigu.comp"})
@Configuration
public class MainConfigOfLifeCycle {

//    @Bean(initMethod = "init",destroyMethod = "destory")
//    Car car(){
//        return new Car();
//    }
//    @Bean
//    Cat cat(){
//        return new Cat();
//    }
    @Bean
    MyBeanPostProcessor pig(){
        return new MyBeanPostProcessor();
    }

}
