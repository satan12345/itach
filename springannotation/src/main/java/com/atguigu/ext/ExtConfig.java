package com.atguigu.ext;

import com.atguigu.comp.Car;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 扩展原理
 * BeanPostProcessor:bean后置处理器,bean创建对象初始化前后拦截工作的
 * BeanFactoryPostProcessor:beanFactory的后置处理器
 *  在BeanFactory标准初始化之后调用：所有bean的定义已经加载到beanFactory中
 *
 *  1.IOC容器创建对象
 *  2.invokeBeanFactoryPostProcessors(beanFactory);执行 postProcessBeanFactory
 *  如何找到所有的BeanFactoryProcessor
 *      1 直接在BeanFactory中找到所有类型是BeanFactoryPostProcessor的组件 并执行他们的方法
 *      2 在初始化其他组件前面执行
 *
 *
 *
 *
 * 2.BeanDefinitionRegistryPostProcessor extends BeanFactoryPostProcessor
 *   postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry)
 *   在所有bean的定义信息将要被加载 但是bean的实例还未创建的时候执行
 *   优先于BeanFactoryPostProcessor执行：利用BeanDefinitionRegistryPostProcessor给容器中再额外添加一些组件
 *  原理:
 *      1)IOC创建对象
 *      2)refresh()->invokeBeanFactoryPostProcessors(beanFactory);
 *      3)从容器中获取到所有BeanDefinitionRegistryPostProcessor组件
 *        依次触发所有的postProcessBeanDefinitionRegistry的方法
 *        再触发postProcessBeanFactory的方法
 *      4）再从容器中找到BeanFactoryPostProcessor组件 然后依次触发postProcessBeanFactory()方法
 *
 *
 *
 * 3.ApplicationListener 监听容器中发布的事件 完成事件模型驱动开发
 *  public interface ApplicationListener<E extends ApplicationEvent>
 *      监听ApplicationEvent 及其下面的子事件
 *
 *      1.写一个监听器来监听某个事件(ApplicationEvent及其子类)
 *      @EventListene;
 *       原理?使用EventListenerMethodProcessor
 *          SmartInitializingSingleton
 *           1.IOC容器创建对象 并刷新容器
 *           2finishBeanFactoryInitialization(beanFactory); 初始化剩下的单实例bean
 *             1.先创建所有的单实例bean
 *             2.获取所有创建好的单实例bean 判断是否是 SmartInitializingSingleton这个类型 如果是则调用
 *              smartSingleton.afterSingletonsInstantiated();
 *      2.把监听器加入到容器中
 *      3.只要容器中有相关事件的发布 我们就能监听到这个事件
 *              ContextRefreshedEvent:容器刷新完成(所有bean都完全创建)会发布这个事件.
 *              ContextClosedEvent:关闭容器会发布这个事件
 *       4.发布一个事件
 *
 *       原理：
 *          ContextRefreshedEvent
 *          ContextClosedEvent
 *
 *          ContextRefreshedEvent:
 *           1.容器创建对象
 *           2.refresh();刷新容器
 *           3.finishRefresh();
 *           4publishEvent(new ContextRefreshedEvent(this));
 *              事件发布流程
 *              getApplicationEventMulticaster().multicastEvent(applicationEvent, eventType);
 *              获取事件多播器(派发器)
 *              获取所有applicationListeners
 *              for (final ApplicationListener<?> listener : getApplicationListeners(event, type))
 *              如果有Executor 可以支持使用Executor进行异步派发
 *              否则 同步的方式直接执行listener方法:invokeListener(listener, event)
 *              拿到listener回调onApplicationEvent方法
 *
 *        事件多播器：
 *          1.容器创建对象:refresh();
 *          2. initApplicationEventMulticaster();初始化ApplicationEventMulticaster;
 *                  1 先去容器中去找有没有id="applicationEventMulticaster"的组件
 *                  2 如果没有则创建一个 并将其加入到spring容器中去
 *                  this.applicationEventMulticaster = new SimpleApplicationEventMulticaster(beanFactory);
                    beanFactory.registerSingleton(APPLICATION_EVENT_MULTICASTER_BEAN_NAME, this.applicationEventMulticaster);
                    我们就可以再其他组件需要派发事件的时候 自动注入applicationEventMulticaster这个组件




        容器中有哪些监听器
            1.容器创建对象:refresh();
            2.registerListeners();//注册监听器 从容器中拿到所有的监听器 把他们注册到applicationEventMulticaster中;
            3.String[] listenerBeanNames = getBeanNamesForType(ApplicationListener.class, true, false);


 *
 *
 *
 *
 *
 *
 *
 *
 */
@ComponentScan({"com.atguigu.ext"})
@Configuration
public class ExtConfig {

    @Bean(initMethod = "init",destroyMethod ="destory" )
    Car car(){
        return new Car();
    }
}
