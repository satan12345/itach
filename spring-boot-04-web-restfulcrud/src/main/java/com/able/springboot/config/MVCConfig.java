package com.able.springboot.config;

import org.springframework.boot.web.embedded.jetty.JettyServletWebServerFactory;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.concurrent.TimeUnit;

/*
扩展SpringMVC的功能
 */
@Configuration
public class MVCConfig implements WebMvcConfigurer {


//    @Bean
//    ConfigurableWebServerFactory configurableWebServerFactory() {
//        JettyServletWebServerFactory jettyServletWebServerFactory = new JettyServletWebServerFactory();
//        jettyServletWebServerFactory.setPort(8083);
//        return jettyServletWebServerFactory;
//    }
@Bean
public ConfigurableServletWebServerFactory webServerFactory() {
    TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();
    factory.setPort(9000);
    factory.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/notfound.html"));
    return factory;
}


    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/able").setViewName("success");

    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //增加登录拦截器
        //静态资源:  .css .js
        //springBoot 已经做好了静态资源的映射
//        registry.addInterceptor(new LoginHandlerInterceptor())
//                .addPathPatterns("/**")
//                .excludePathPatterns(Arrays.asList("/","index.html","/user/login"));
    }

    /**
     * 所有的WebMvcAdapter都会一起起作用
     *
     * @return
     */
    @Bean
    WebMvcConfigurer webMvcConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/").setViewName("login");
                registry.addViewController("/index.html").setViewName("login");
                registry.addViewController("/main.html").setViewName("dashboard");
            }
        };
    }


}
