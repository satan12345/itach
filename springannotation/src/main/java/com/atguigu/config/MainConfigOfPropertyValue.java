package com.atguigu.config;

import com.atguigu.bean.Person;
import com.atguigu.comp.Car;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
//使用@PropertySource读取外部配置文件中的k/v数据保存到运行环境变量中
@PropertySource({"classpath:person.properties"})
@ComponentScan(basePackages = {"com.atguigu"})
@Configuration
public class MainConfigOfPropertyValue {

    @Bean
    public Person person(){
        return new Person();
    }
    @Bean
    public Car car(){
        return new Car();
    }
}
