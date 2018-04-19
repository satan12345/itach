package com.atguigu.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect//表示当前类是一个切面
@Component
public class LogAspects {
    @Pointcut("execution(public * com.atguigu.aop.*.*(..))")
    public void  pointCut(){}

    @Before("pointCut()")
    public void logStart(JoinPoint joinPoint){
        String methodName=joinPoint.getSignature().getName();
        Object[] args=joinPoint.getArgs();
        System.out.println("切面开始,方法名称为:"+methodName+",参数为:"+args);

    }
    @After("pointCut()")
    public void logEnd(JoinPoint joinPoint){

        System.out.println("方法"+joinPoint.getSignature().getName()+"切面结束");

    }
    @AfterReturning(value = "pointCut()",returning = "result")
    public void logReturn(JoinPoint joinPoint,Object result){
        System.out.println(joinPoint.getSignature().getName()+"切面有返回值,返回的值为:"+result);

    }
    @AfterThrowing(value = "pointCut()",throwing = "exception")
    public void logException(JoinPoint joinPoint,Exception exception){
        System.out.println(joinPoint.getSignature().getName()+"切面有异常,异常信息为"+exception);

    }
}
