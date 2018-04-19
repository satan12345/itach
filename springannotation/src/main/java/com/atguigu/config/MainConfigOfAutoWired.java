package com.atguigu.config;

import org.springframework.context.annotation.Configuration;

/**
 * 自动装配：
 *  spring 利用依赖注入(DI) 完成对IOC 容器中各个组件的依赖关系赋值
 *  1 @AutoWired:自动注入
 *  默认优先按照类型去容器中找对应的主键：applicationContext.getBean(BookDao.class)
 *  如果找到多个相同类型的组件 在将属性的名称作为组件的id去容器中查找
 *  @Qualifier("bookDao") 使用@Qualifier 指定需要装配的组件的id 而不是使用属性名
 *  自动装配默认一定要蒋属性赋值好 没有就会报错; 指定@AutoWired(required=false)属性 设置为非必须
 *  @Primary 设置首选的装配Bean
 *
 * Spring还支持使用@Resource(Jsr250)和@Inject(JSR330)[java规范的注解]
 * @Resource 可以和@Autowired一样实现自动装配功能 默认按照组件名字装配
 * 没有支持@Primary功能以及 required=false的功能
 * @Inject
 *  需要导入javax.inject的包 和Autowired的功能一样
 *
 *
 *  @AutowiRed:构造器 参数 方法 属性:都是从容器中获取参数组件的值
 *  1 标注在方法位置：@Bean+方法参数 参数从容器中获取，默认不写@AutoWired效果是一样的 都能自动注入
 *  2 标注在构造器上：如果组件只有一个游蚕构造器 这个有参构造器的@Autowired可以省略
 *  3 放在参数位置
 *
 *
 *  自定义组件想要使用Spring容器底层的一些组件(ApplicationContext BeanFactory,xxx)
 *  自定义组件只要实现xxxAware接口;在创建Bean的时候 会调用接口规定的方法注入相关组件：Aware;
 *  把Spring底层一些组件注入到自定义的Bean中
 */
@Configuration
public class MainConfigOfAutoWired {

}
