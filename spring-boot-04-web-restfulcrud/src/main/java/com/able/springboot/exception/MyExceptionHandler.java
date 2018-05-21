package com.able.springboot.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class MyExceptionHandler {
//    @ResponseBody
//    @ExceptionHandler(UserNotFondException.class)
//    public Map<String,Object> handleException(Exception e){
//        Map<String,Object> map=new HashMap<>(3);
//        map.put("code","user not exist");
//        map.put("message",e.getMessage());
//        return map;
//
//    }

    @ExceptionHandler(UserNotFondException.class)
    public String handleException(Exception e, HttpServletRequest request){
        Map<String,Object> map=new HashMap<>(3);
        //Integer statusCode = (Integer) request
        //				.getAttribute("javax.servlet.error.status_code");
        //传入我们自己的错误状态码 4xx  5xx

        request.setAttribute("javax.servlet.error.status_code",500);
        map.put("code","user not exist");
        map.put("message",e.getMessage());
        request.setAttribute("ext",map);
        return "forward:/error";
    }


}
