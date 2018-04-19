package com.atguigu.tx;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * 环境搭建：
 *      1 导入相关依赖：数据源 数据库驱动 spring-jdbc模块
 *      2 配置数据源 jdbcTemplate(spring提供简化数据库操作的工具)操作数据
 *      3 给方法上标注 @Transactional 表示当前方法是一个事务方法
 *      4 @EnableTransactionManagement 开启基于注解的事务管理功能
 *
 * 原理：
 *  1.@EnableTransactionManagement
 *      利用TransactionManagementConfigurationSelector给容器中导入组件
 *      导入两个组件
 *          AutoProxyRegistrar
 *          ProxyTransactionManagementConfiguration
 *
 *  2 AutoProxyRegistrar:
 *       给容器中注册一个InfrastructureAdvisorAutoProxyCreator组件
 *       利用后置处理器机制在对象创建之后 包装对象 返回一个代理对象 代理对象利用拦截器链进行调用
 *  3 ProxyTransactionManagementConfiguration:
 *          给容器中注册事务增强器： BeanFactoryTransactionAttributeSourceAdvisor
 *              1事务增强器要用事务注解信息:AnnotationTransactionAttributeSource
 *              2事务拦截器：
 *                      TransactionInterceptor：保存了事务的属性信息,事务管理器
 *                      他是一个methodInterceptor
 *                      在目标方法执行的时候：
 *                      执行拦截器链
 *                          事务拦截器：
 *                              1 先获取事务相关的属性
 *                              2再获取PlatformTransactionManager,如果没有添加指定任何transactionManager
 *                              最终会从容器中按照类型获取一个PlatformTransactionManager
 *                              3 执行目标方法
 *                                如果异常 获取到事务管理器 利用事务管理 回滚操作
 *                                如果正常 获取到事务管理器 利用事务管理器 提交事务
 *
 *
 */

@Configuration
@PropertySource({"classpath:db.properties"})
@ComponentScan({"com.atguigu.tx"})
@EnableTransactionManagement
public class TxConfig {

    @Value("${db.user}")
    private String user;
    @Value("${db.password}")
    private  String password;
    @Value("${db.driverClass}")
    private String driverClass;
    @Bean
    public DataSource dataSource() throws Exception {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setUser(user);
        dataSource.setPassword(password);
        dataSource.setJdbcUrl("jdbc:mysql://118.25.49.75:3306/dev");
        dataSource.setDriverClass(driverClass);
        return dataSource;
    }
    //注册事务管理器在容器中
    @Bean
    public PlatformTransactionManager platformTransactionManager() throws Exception {
        return new DataSourceTransactionManager(dataSource());
    }

    @Bean
    public JdbcTemplate jdbcTemplate() throws Exception{
        return new JdbcTemplate(dataSource());
    }
}
