package com.able.concurrency.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicBoolean;

@RestController
@Slf4j
public class TestController {
    AtomicBoolean atomicBoolean=new AtomicBoolean(true);

    @RequestMapping("test")
    public String test(){
        if (atomicBoolean.compareAndSet(true,false)) {

        }
        return "test";

    }



}
