package com.atguigu.config;

import com.atguigu.bean.Person;
import com.atguigu.service.BookService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

//配置类==配置文件
@Configuration//告诉Spring这是一个配置类
@ComponentScan(
        basePackages = {"com.atguigu"},//扫描指定包
//        excludeFilters = {//排除
//                @ComponentScan.Filter(type = FilterType.ANNOTATION, value = {Controller.class}),
//                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = {BookService.class}),
//                @ComponentScan.Filter(type = FilterType.CUSTOM,value = {MyFilter.class})
//      }
        //FilterType.ANNOTATION 按照注解
        //FilterType.ASSIGNABLE_TYPE 按照给定的类型
        //FilterType.ASPECTJ 使用ASPECTJ表达式
        //FilterType.REGEX 使用正则表达式
        //FilterType.CUSTOM 使用自定义规则

        includeFilters = {//包含
//                @ComponentScan.Filter(type = FilterType.ANNOTATION, value = {Controller.class}),
                @ComponentScan.Filter(type = FilterType.CUSTOM, value = {MyFilter.class})
        },
        useDefaultFilters = false//想使用includeFilters 则必须禁用掉默认的过滤规则
)
public class MainConfig {

    @Bean(name = "Person")
    public Person person() {
        return new Person("卡卡西", 20);
    }

}
