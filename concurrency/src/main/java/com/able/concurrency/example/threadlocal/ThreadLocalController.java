package com.able.concurrency.example.threadlocal;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Administrator
 */
@RestController
@RequestMapping("threadLocal")
public class ThreadLocalController {

    @RequestMapping("test")
    public String test(){
        return "宇智波鼬:"+ThreadLocalHolder.getId();
    }
}
