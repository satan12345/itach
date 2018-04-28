package com.able.springboot.config;

import com.able.springboot.controller.HelloController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Configuration 指明当前类是一个配置类;就是来替代之前的Spring配置文件
 */
@Configuration
public class MyAppConfig {

    @Bean
    HelloController helloController(){
        return new HelloController();
    }

}
