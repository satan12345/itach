package com.atguigu.config;

import com.atguigu.bean.Person;
import com.atguigu.comp.Color;
import com.atguigu.comp.ColorFactoryBean;
import com.atguigu.comp.Red;
import com.atguigu.condition.LinuxCondition;
import com.atguigu.condition.MyImportBeanDefinitionRegistrar;
import com.atguigu.condition.MySelector;
import com.atguigu.condition.WindowsCondition;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.*;
//定义在类上 表示只有满足当前条件  这个类配置的所有的bean才能生效
@Conditional({WindowsCondition.class})
@Configuration
@Import({Color.class, Red.class,
        MySelector.class,
        MyImportBeanDefinitionRegistrar.class})
public class MainConfig2 {

    @Bean
    /**
     * Specifies the scope to use for the annotated component/bean.
     * @see ConfigurableBeanFactory#SCOPE_SINGLETON singleton 单例
     * @see ConfigurableBeanFactory#SCOPE_PROTOTYPE prototype 多例
     * @see org.springframework.web.context.WebApplicationContext#SCOPE_REQUEST 一次请求创建一个实例
     * @see org.springframework.web.context.WebApplicationContext#SCOPE_SESSION 一个Session创建一个实例
     */
    @Scope()
    @Lazy()
    /**
     * 懒加载
     * 单实例：默认在容器启动的时候创建对象
     * 懒加载：容器启动不创建对象 第一次获取bean对象的时候创建实例
     */
    Person person() {
        return new Person("宇智波鼬", 15);
    }

    /**
     * @Conditional ：按照一定的条件进行判断 满足条件给容器注册bean
     */
    @Conditional({WindowsCondition.class})
    @Bean(name = "windows")
    Person person01() {
        return new Person("比尔盖茨", 88);
    }

    @Conditional({LinuxCondition.class})
    @Bean(name = "linux")
    Person person02() {
        return new Person("linux To", 30);
    }
    /**
     * 给容器中注册组件
     * 1 包扫描+组件标注注解
     *          @Controller @Service @Repository @Component
     * 2 @Bean[导入第三方包里的组件]
     * 3 @Import 快速给容器中导入一个组件
     *      @Import(要导入到容器中的组件)：容器中就会自动注册这个组件 id默认是全类名
     *      ImportSelector:返回需要导入的组件的全类名数组
     *      ImportBeanDefinitionRegistrar:手动注册bean到容器中
     * 4 使用Spring提供的FactoryBean(工厂Bean)
     *     默认获取到的是工厂bean调用getObject创建的对象
     *     要获取工厂Bean本身 需要给id前面加一个& 如：&clolrFactoryBean
     *
     */
    @Bean
    public ColorFactoryBean colorFactoryBean(){
        return new ColorFactoryBean();
    }
}
