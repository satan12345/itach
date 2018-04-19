package com.atguigu.config;

import com.atguigu.aop.LogAspects;
import com.atguigu.aop.MathCalculator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * AOP:【动态代理】
 * 指在成员运行期间动态的将某段代码切入到指定方法指定位置进行运行的编程方式
 *
 * 1 导入aop模块：spring-aop:(spring-aspects)
 * 2 定义一个业务逻辑类(MathCalculator) 在业务路径运行的时候进行打印（方法之前,方法运行结束,方法出现异常）
 * 3 定义一个日志切面类(LogAspects):切面类里面的方法需要动态感知MathCalculator.div运行到哪里 然后执行
 *      通知方法
     *      前置通知(@Before)：在目标方法 运行之前运行
 *          后置通知(@After): 在目标方法 运行结束之后运行(无论是正常结束 还是异常结束 都调用)
     *      返回通知(@AfterReturn)：在目标方法正常返回之后运行
     *      异常通知(@AfterThrowing)：在目标方法运行出现异常以后运行
     *      环绕通知(@Around)：动态代理,手动推进目标方法运行(joinPoint.procced())
 *  4 给切面类的目标方法何时何地运行
 *  5 将切面类与业务逻辑类(目标方法所在类)都加入到容器中
 *  6 告诉Spring哪个类是切面类
 *  7 开启基于注解的aop模式 @EnableAspectJAutoProxy
 *    在Spring中很多@EnableXXX;
 * 三步:
 *   1 将业务逻辑组件和前面类都加入到容器中,告诉Spring哪个是切面类(@Aspect)
 *   2 在切面类上的每一个通知方法上标准通知注解 告诉Spring何时何地运行(pointCut)
 *   3 开启基于注解的配置文件
 *
 * AOP原理: 看给容器中注册了什么组件 这个组件什么时候工作 这个组件的功能是什么
 *  @EnableAspectJAutoProxy
 *  1 @EableAspectJautoProxy 是什么?
 *          @Import(AspectJAutoProxyRegistrar.class) 给容器中导入AspectJAutoProxyRegistrar 这个组件
 *          利用AspectJAutoProxyRegistrar 自定义给容器中注册beran
 *          internalAutoProxyCreator=AnnotationAwareAspectJAutoProxyCreator
 *          org.springframework.aop.aspectj.annotation.AnnotationAwareAspectJAutoProxyCreator=org.springframework.aop.config.internalAutoProxyCreator
 *
 *          给容器中注册一个AnnotationAwareAspectJAutoProxyCreator:
 *
 *
 *          AnnotationAwareAspectJAutoProxyCreator
 *              AspectJAwareAdvisorAutoProxyCreator
 *                  AbstractAdvisorAutoProxyCreator
 *                      AbstractAutoProxyCreator
 *                              implements SmartInstantiationAwareBeanPostProcessor, BeanFactoryAware
 *                              关注后置处理器(Bean初始化完成前后做的事情),自动装配BeanFactory
 *                          ProxyProcessorSupport
 *        AbstractAutoProxyCreator.setBeanFactory
 *        AbstractAutoProxyCreator.有后置处理器的逻辑:
 *
 *        AbstractAdvisorAutoProxyCreator.setBeanFactory()-->initBeanFactory();
 *
 *        AspectJAwareAdvisorAutoProxyCreator:
 *
 *        AnnotationAwareAspectJAutoProxyCreator:
 *
 *        流程：
 *          传入配置类 创建IOC容器
 *          注册配置类 调用refresh() 刷新容器
 *
 *          // Register bean processors that intercept bean creation.
            registerBeanPostProcessors(beanFactory);注册bean的后置处理器来方便拦截bean的创建
                1.先获取IOC容器中已经定义了的需要创建对象的所有BeanPostProcessor
                2.给容器中加入别的BeanPostProcessor
                3.优先注册实现了PriorityOrdered接口的BeanPostProcessor
                4.再给容器中注册实现了Ordered接口的BeanPostProcessor
                5.注册没实现优先级接口的BeanPostProcessor
                6.注册BeanPostProcessor 实际上就是创建BeanPostProcessor对象 保存在容器中
                    创建internalAutoProxyCreator 的BeanPostProcessor
                      创建Bean的实例:doCreateBean(final String beanName, final RootBeanDefinition mbd, final Object[] args)
                      populateBean(beanName, mbd, instanceWrapper);给Bean的各种属性赋值
                      initializeBean:初始化bean
                            1.invokeAwareMethods:处理aware接口的方法回调
                            2.applyBeanPostProcessorsBeforeInitialization:应用后置处理器的postProcessBeforeInitialization方法
                            3.invokeInitMethods(beanName, wrappedBean, mbd);执行自定义的初始化方法
                            4.applyBeanPostProcessorsAfterInitialization:执行后置处理器的postProcessAfterInitialization方法
                      BeanPostProcessor
                7把BeanFactoryProcessor注册到BeanFactory中
                 BeanFactory.addBeanPostProcessor(postProcessor);
        ===以上是创建和注册AnnotationAwareAspectJAutoProxyCreator的过程======
        // Instantiate all remaining (non-lazy-init) singletons. 完成BeanFactory的初始化工作：创建剩下的单实例Bean
        4 finishBeanFactoryInitialization(beanFactory);
            1 遍历获取容器中所有的Bean 依次创建对象getBean(beanName)
              getBean-->doGetBean-->getSingleton
            创建Bean
                【AnnotationAwareAspectJAutoProxyCreator 在所有Bean创建之前会有一个拦截 因为他是InstantiationAwareBeanPostProcessor 这个后置处理器
                所以会调用postProcessBeforeInstantiation这个方法】
                1 先从缓存在获取当前bean 如果能获取到 说明Bean是之前被创建过的 直接使用 否则在创建
                只要创建好的bean都会被缓存起来

                2 createBean(beanName, mbd, args);创建Bean
                    【BeanPostProgressor是在Bean对象创建完成初始化之后前后调用】
                    【InstantiationAwareBeanPostProcessor是在Bean实例之前就尝试用后置处理器返回对象】
                    // Give BeanPostProcessors a chance to return a proxy instead of the target bean instance.
                    Object bean = resolveBeforeInstantiation(beanName, mbdToUse); 希望后置处理器在此能返回一个代理对象 如果能返回一个代理对象 就使用
                        如果不能 就继续调用 doCreateBean
                        后置处理器先尝试返回对象
                        bean = applyBeanPostProcessorsBeforeInstantiation(targetType, beanName);
                            拿到所有的后置处理器 如果是InstantiationAwareBeanPostProcessor 则调用postProcessBeforeInstantiation方法
                        bean = applyBeanPostProcessorsAfterInitialization(bean, beanName);

AnnotationAwareAspectJAutoProxyCreator【InstantiationAwareBeanPostProcessor】的作用：
    1每个Bean创建之前 调用PostProcessBeforeInstantiation();
        关心MathCalculator和LogAspect的创建:
            当前bean是否在advisedBeans中（保存了所有需要增强的Bean）
            判断当前bean是否是基础类型 Advice.class  Pointcut.class
            Advisor.class AopInfrastructureBean.class或者是否是切面(@Aspect)
       3是否需要跳过
            1 获取候选的增强器(切面里面的通知方法)【list<Advisor> candodateAdvosprs】
                每个封装通知方法的增强器是InstantiationModelAwarePointcutAdvisor类型
                判断每个增强器是否是AspectJPointcutAdvisor这个类型
            2 永远返回false

     2创建对象
     3postProcessAfterInitialization
        wrapIfNecessary(bean, beanName, cacheKey)
        1 获取当前bean的所有增强器（通知方法）
          1 找到候选的所有的增强器（找到哪些通知方法是需要切入当前bean方法的）
          2 获取到能在当前bean中使用的增强器
          3 给增强器进行排序
        2 保存当前bean在AdviseBeans中(表示当前Bean已经被增强处理了)
        3 如果当前bean需要增强 创建当前bean的代理对象
            获取所有增强器(通知方法)
            保存到proxyFactory
            创建代理对象
                JdkDynamicAopProxy  jdk动态代理
                ObjenesisCglibAopProxy cglib的动态代理
        4 给容器中返回当前组件使用cglib增强了的代理对象
        5 以后容器中获取到的就是这个组件的代理对象 执行目标方法的时候 代理对象就会执行通知方法的流程

目标方法的执行：
        容器中保存了组件的代理对象(cglib增强后的对象),这个对象里面保存了详细信息(比如增强器 比如目标对象 xxx)

        1cglibAopProxy.intercept();拦截目标方法的执行
        2根据ProxyFactory获取目标方法的拦截器链
          List<Object> chain = this.advised.getInterceptorsAndDynamicInterceptionAdvice(method, targetClass)
            1 List<Object> interceptorList//保存所有拦截器
            2 遍历所有的增强器 将其转换成Interceptor;
                registry.getInterceptors
            3 将增强器转换为List<MethodInterceptor>
              如果是MethodInterceptor 将其加入到集合中
              如果不是 使用AdvisorAdapter将增强器转为interceptor
              转换完成 返回列表

        3如果没有拦截器链 直接执行目标方法
            拦截器链(每一个通知方法又被包装为拦截器（利用methodInterceptor)
        4 如果有拦截器链,把目标对象 目标方法 目标方法 拦截器链等信息传入 创建一个CglibMethodInvocation 并调用proceed方法
retVal = new CglibMethodInvocation(proxy, target, method, args, targetClass, chain, methodProxy).proceed()

        5拦截器链的触发过程:
                如果没有拦截器 直接执行目标方法 或者拦截器的索引和拦截器数组-1大小一样（指定到了最后一个拦截器） 执行目标方法
                链式获取每一个拦截器,拦截器执行invoke方法 每一个拦截器等待下一个拦截器执行完成后再来执行
                拦截器链的机制 包装了通知方法与目标方法的执行顺序。

 *总结: 1 @EnableAspectJAutoProxy 开启AOP功能
 *     2 @EnableAspectJAutoProxy 会给容器中注册一个组件 AnnotationAwareAspectJAutoProxyCreator
 *     3 AnnotationAwareAspectJAutoProxyCreator是一个后置处理器
 *     4 容器的创建流程：
 *       1 registerBeanPostProcessors注册后置处理器
 *       2 finishBeanFactoryInitialization 初始化剩下的单实例Bean
 *         1创建业务逻辑组件 与切面组件
 *         2AnnotationAwareAspectJAutoProxyCreator拦截组件的创建过程
 *         3判断组件是否需要增强
 *          是：切面的通知方法 包装成增强器给业务逻辑组件创建代理对象
 *     5 执行目标方法：代理对象要执行目标方法
 *          1 代理对象执行目标方法
 *            CGlinAOPPRoxy.intercept()
 *             得到目标方法的拦截器链(增强方法包装成的MethodInterceptor)
 *             利用拦截器的链式机制 依次进入每个拦截器进行执行
 *              效果:
 *                  正常执行:前置通知-》目标方法-》后置通知->返回通知
 *                  异常执行:前置通知-》目标方法-》后置通知->异常通知
 *
 *
 *
 *
 *
 *
 *
 */
//@ComponentScan(basePackages = {"com.atguigu.aop"})
@Configuration
@EnableAspectJAutoProxy
public class MainConfigOfAOP {
    @Bean
    LogAspects logAspects(){
        return new LogAspects();
    }
    @Bean
    MathCalculator mathCalculator(){
        return new MathCalculator();
    }

}
