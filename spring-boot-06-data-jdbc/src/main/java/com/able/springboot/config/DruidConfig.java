package com.able.springboot.config;

import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DruidConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    DataSource dataSource() {
        return new DruidDataSource();
    }

    //配置Druid的监控

    /**配置一个管理后台的Servlet
     *
     * @return
     */
    @Bean
    public ServletRegistrationBean statViewServlet() {
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet());
        servletRegistrationBean.setUrlMappings(Arrays.asList("/druid/*"));
        Map<String,String> initParam=new HashMap<>(0);
        initParam.put(StatViewServlet.PARAM_NAME_USERNAME,"admin");
        initParam.put(StatViewServlet.PARAM_NAME_PASSWORD,"123456");
        //默认运行所有
        initParam.put(StatViewServlet.PARAM_NAME_ALLOW,"localhost");
        initParam.put(StatViewServlet.PARAM_NAME_DENY,"192.168.15.21");
        servletRegistrationBean.setInitParameters(initParam);
        return servletRegistrationBean;
    }
    /**配置一个监控的filter
     *
     */
    @Bean
    public FilterRegistrationBean webStatFilter(){
        FilterRegistrationBean filterRegistrationBean=new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new WebStatFilter());
        Map<String,String> initParam=new HashMap<>(0);
        initParam.put(WebStatFilter.PARAM_NAME_EXCLUSIONS,"*.js,*.css,/druid/*");
        filterRegistrationBean.setInitParameters(initParam);
        filterRegistrationBean.setUrlPatterns(Arrays.asList("/"));
        return filterRegistrationBean;

    }


}


