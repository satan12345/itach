package com.atguigu;

import com.atguigu.config.AppConfig;
import com.atguigu.config.RootConfig;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

//WEB容器启动的时候创建对象 调用方法来初始化容器以及前端控制器
public class MyWebApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    //获取根容器的配置类:(spring的配置文件)父容器,
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{RootConfig.class};
    }
    //获取Web容器的配置类(springmvc配置文件) 子容器
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{AppConfig.class};
    }
    //获取dispatcherServlet的映射信息

    /**
     * /:拦截所有请求(包括静态资源(xx.js,xx.png)) 但是不包括*.jsp
     * /*:拦截所有请求 连*.jsp页面都拦截; jsp页面是tomcat的jsp引擎解析的
     * @return
     */
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
