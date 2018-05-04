package com.able.controller;

import com.able.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @Autowired
    HelloService helloService;
    @ResponseBody
    @RequestMapping("hello")
    public String hello(){
        return helloService.sayHello("kakaxi");
    }
}
