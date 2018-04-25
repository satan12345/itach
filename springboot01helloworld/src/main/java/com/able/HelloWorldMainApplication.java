package com.able;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
/* *
 @SpringBootApplication:标注一个主程序 说明这是一个spring boot应用
 */
@SpringBootApplication
public class HelloWorldMainApplication {
    @RequestMapping("/")
    @ResponseBody
    String home() {
        return "Hello World!";
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(HelloWorldMainApplication.class, args);
    }

}
