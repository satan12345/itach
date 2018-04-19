package com.atguigu.condition;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;
//判断是否是linux系统
public class LinuxCondition implements Condition {
    /* *
     *
     * @author jipeng
     * @Description LinuxCondition
     * @date 2018/4/9 8:51
     * @param [context, 判断条件上下文环境
      * metadata 注解的原数据信息
    ]
     * @return boolean
     */
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        ConfigurableListableBeanFactory beanFactory=context.getBeanFactory();//获取工厂
        ClassLoader classLoader = context.getClassLoader();//获取类加载器
        Environment environment = context.getEnvironment();//获取环境
        BeanDefinitionRegistry registry = context.getRegistry();//获取bean定义的注册类

        boolean person = registry.containsBeanDefinition("person");
        System.out.println("是否包含" + person);
        String property = environment.getProperty("os.name");
        if (property.toLowerCase().contains("linux")){
            return true;
        }

        return false;
    }
}
