package com.atguigu.config;

import com.atguigu.intercept.MyFirstIntercept;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.*;

/**
 * springmvc只扫描Controller 是一个子容器
 */
@ComponentScan(basePackages = {"com.atguigu"},
        includeFilters = {
        @ComponentScan.Filter(type = FilterType.ANNOTATION,classes = {Controller.class})
},
        useDefaultFilters = false
)
@EnableWebMvc
public class AppConfig extends WebMvcConfigurerAdapter {
    //定制


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/img/**")
                .addResourceLocations("/img/");
    }

    //视图解析器
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        //return jsp("/WEB-INF/", ".jsp");
        registry.jsp("/WEB-INF/views/",".jsp");
    }
    //设置默认处理器
//    @Override
//    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
//        configurer.enable();
//    }
    //拦截器


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MyFirstIntercept()).addPathPatterns("/**");
    }
}
