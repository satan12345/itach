package com.able.springboot.controller;

import com.able.springboot.exception.UserNotFondException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;

@Controller
public class HelloController {

    @ResponseBody
    @RequestMapping("hello")
    public String hello(String name){
        if ("aaa".equals(name)) {
            throw new UserNotFondException();
        }
        return "hello world";
    }

    @RequestMapping("success")
    public ModelAndView success(){
        ModelAndView mv=new ModelAndView("success");
        mv.addObject("name","卡卡西");
        mv.addObject("users", Arrays.asList("张三","李四","王五"));
        return mv;
    }



}
