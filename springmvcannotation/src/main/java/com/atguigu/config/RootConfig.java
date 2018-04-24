package com.atguigu.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;

/**
 * spring的容器不扫描controller;父容器
 */
@ComponentScan(basePackages = {"com.atguigu"},
        excludeFilters ={
        @ComponentScan.Filter(type = FilterType.ANNOTATION,classes = {Controller.class})
        }
)
public class RootConfig {
}
