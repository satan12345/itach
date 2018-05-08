package com.able.concurrency;

import com.able.concurrency.example.threadlocal.HttpIntercept;
import com.able.concurrency.example.threadlocal.MyHttpFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Administrator
 */
@SpringBootApplication
public class ConcurrencyApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(ConcurrencyApplication.class, args);
	}

	@Bean
	public FilterRegistrationBean filterRegistrationBean(){
		FilterRegistrationBean filterRegistrationBean=new FilterRegistrationBean();
		filterRegistrationBean.setFilter(new MyHttpFilter());
		filterRegistrationBean.addUrlPatterns("/threadLocal/*");
		return filterRegistrationBean;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new HttpIntercept()).addPathPatterns("/**");

	}
}
