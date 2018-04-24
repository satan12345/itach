package com.atguigu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

@Controller
public class AsyncController {

    @ResponseBody
    @RequestMapping("/createOrder")
    public DeferredResult<Object> createOrder(){
        DeferredResult<Object> deferredResult=new DeferredResult<>(3000L,"create failed");

        return deferredResult;
    }
    /**
     * 控制器返回Callable
     * springmvc异步处理,将Callable提交到TaskExecutor 使用一个隔离的线程进行执行
     * DispatcherServlet和所有的Filter退出web线程,但是response保持打开状态;
     * Callable返回结果 springmvc将请求重新派发给容器,恢复之前的处理
     * 根据Callable返回的结果Springmvc继续进行视图渲染流程等(从接收请求再次到视图渲染)
     *    preHandle
         主线程开始:http-bio-80-exec-1==>1524540679477
         主线程结束:http-bio-80-exec-1==>1524540679477
     =======DispatcherServlet和所有的Filter退出web线程============
     ============等待Callable执行=====================
         副线程开始:MvcAsync1==>1524540679484
         副线程结束:MvcAsync1==>1524540681484
     ============Callable执行完成=====================

     ================================================
         preHandle
         postHandle (Callable的之前的返回值就是目标方法的返回值)
         postHandle

     异步拦截器:
        原生api的AsyncListener
       springMVC： AsyncHandlerInterceptor
     */
    @ResponseBody
    @RequestMapping("async01")
    public Callable<String> async01(){
        System.out.println("主线程开始:" + Thread.currentThread().getName() + "==>" + System.currentTimeMillis());
        Callable<String> callable = () -> {
            System.out.println("副线程开始:" + Thread.currentThread().getName() + "==>" + System.currentTimeMillis());
            TimeUnit.SECONDS.sleep(2);
            System.out.println("副线程结束:" + Thread.currentThread().getName() + "==>" + System.currentTimeMillis());
            return " Callable<String> async01";
        };
        System.out.println("主线程结束:" + Thread.currentThread().getName() + "==>" + System.currentTimeMillis());
        return callable;
    }
}
