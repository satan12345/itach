package com.able.springboot.component;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginHandlerInterceptor extends HandlerInterceptorAdapter {

    /***

      @author jipeng
      @Description 目标方法执行之前执行
      @date 2018/5/14 18:18
      @param
      @return boolean
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object loginUser = request.getSession().getAttribute("loginUser");
        if (loginUser != null) {
            return true;
        }
        request.setAttribute("msg","请先登录");
        response.sendRedirect(request.getContextPath() + "/");
        return false;
    }
}
