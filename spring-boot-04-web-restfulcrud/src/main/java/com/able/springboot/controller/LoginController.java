package com.able.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

/***

  @author jipeng
  @Description LoginController
  @date 2018/5/9 17:28
  @param
  @return
 */
@Controller
public class LoginController {
    private static final String DEFAULT_PASSWORD="123456";

    @PostMapping("user/login")
    public String login(String userName, String passWord, Model model,HttpSession session){
        if (!StringUtils.isEmpty(userName)&&DEFAULT_PASSWORD.equals(passWord)) {
            //登录成功 防止表单重复提交 可以重定向到主页
            session.setAttribute("loginUser",userName);
            return "redirect:/main.html";
        }
        model.addAttribute("msg","用户名密码错误");
        return "login";

    }
}
