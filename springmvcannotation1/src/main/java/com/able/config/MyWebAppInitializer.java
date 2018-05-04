package com.able.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
//WEB容器启动的时候创建对象 调用方法来初始容器以及前端控制器
public class MyWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    //获取根容器的配置类(Spring的配置文件) 父容器
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{RootConfig.class};
    }
    //获取WEB容器的配置类(SpringMVC配置文件) 子容器
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{AppConfig.class};
    }
    //获取servlet的映射信息
    //  / 拦截所有请求(包括静态资源(xx.js xx.png )) 但是不包括*.jsp
    //  /* 拦截所有请求  连 *.jsp页面都拦截
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
