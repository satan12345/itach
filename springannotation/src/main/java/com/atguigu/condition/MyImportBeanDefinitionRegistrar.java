package com.atguigu.condition;

import com.atguigu.comp.RainBow;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {
    /* *
     *
     * @author jipeng
     * @Description
     * @date 2018/4/9 17:14
     * @param [importingClassMetadata, 当前类的注解信息
     * registry]  bean定义注册类
     * @return void
     */
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata,
                                        BeanDefinitionRegistry registry) {
        boolean red = registry.containsBeanDefinition("com.atguigu.comp.Red");
        boolean blue = registry.containsBeanDefinition("com.atguigu.comp.Blue");
        if (red&&blue) {
            //注册bean
            registry.registerBeanDefinition("rainbow", new RootBeanDefinition(RainBow.class));
        }
    }
}
