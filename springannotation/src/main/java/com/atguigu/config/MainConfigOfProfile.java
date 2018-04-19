package com.atguigu.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.util.StringValueResolver;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

/**
 * profile:
 *  spring为我们提供的可以根据当前的环境 动态的激活和切换一些了组件的功能
 *  @Profile 指定组件在哪个环境中才能被注册到容器中,不指定任何环境下都能够注册这个组件
 *  加了环境标识的bean  只有这个环境被激活的时候才能注册到容器中 默认是default环境
 *
 *  1使用命令行参数:在虚拟机参数位置加载 -Dspring.profiles.active=test
 *  2 代码的方式激活某种环境
 *      //创建一个applicationcontext
        context=new AnnotationConfigApplicationContext();
        //设置激活的profile
        context.getEnvironment().setActiveProfiles("prod");
        //加载配置类
        context.register(MainConfigOfProfile.class);
        // 刷新启动容器
        context.refresh();
     写在配置类上 只有是指定的环境的时候 整个配置类里面的所有配置才能开始生效
    没有标注环境标识的bean在任何环境下都是加载的
 *
 */
@Profile("test")
@PropertySource({"classpath:db.properties"})
@Configuration
public class MainConfigOfProfile implements EmbeddedValueResolverAware {

    private  StringValueResolver stringValueResolver;
    @Value("${db.user}")
    private String user;
    @Value("${db.password}")
    private  String password;
    @Bean
    @Profile({"dev"})
    public DataSource dataSourceDev() throws PropertyVetoException {
        ComboPooledDataSource dataSource=new ComboPooledDataSource();
        dataSource.setUser(user);
        dataSource.setPassword(password);
        dataSource.setJdbcUrl("jdbc:mysql://118.25.49.75:3306/dev");
        dataSource.setDriverClass(getDriverClass());
        return dataSource;
    }
    @Bean
    @Profile({"test"})
    public DataSource dataSourceTest() throws PropertyVetoException {
        ComboPooledDataSource dataSource=new ComboPooledDataSource();
        dataSource.setUser(user);
        dataSource.setPassword(password);
        dataSource.setJdbcUrl("jdbc:mysql://118.25.49.75:3306/test");
        dataSource.setDriverClass(getDriverClass());
        return dataSource;
    }
    @Bean
    @Profile({"prod"})
    public DataSource dataSourceProd() throws PropertyVetoException {
        ComboPooledDataSource dataSource=new ComboPooledDataSource();
        dataSource.setUser(user);
        dataSource.setPassword(password);
        dataSource.setJdbcUrl("jdbc:mysql://118.25.49.75:3306/prod");
        dataSource.setDriverClass(getDriverClass());
        return dataSource;
    }

    @Override
    public void setEmbeddedValueResolver(StringValueResolver resolver) {
        this.stringValueResolver=resolver;
    }
    private String getDriverClass(){
        return stringValueResolver.resolveStringValue("${db.driverClass}");
    }
}
