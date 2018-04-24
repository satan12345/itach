package com.atguigu.controller;

import com.atguigu.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @Autowired
    HelloService helloService;

    @RequestMapping("hello")
    @ResponseBody
    public String hello(){
        String msg=helloService.sayHello("卡卡西");
        return msg;
    }
    @RequestMapping("success")
    public String success(){
        return "succ";
    }
}
