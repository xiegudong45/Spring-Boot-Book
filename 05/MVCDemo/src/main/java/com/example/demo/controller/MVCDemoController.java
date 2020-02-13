package com.example.demo.controller;

import com.example.demo.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MVCDemoController {
    // 映射url地址
    @GetMapping("/mvcdemo")
    public ModelAndView hello() {
        // 实例化对象
        User user = new User();
        user.setName("xiegudong");
        user.setAge(28);
        // 定义MVC中的视图模板
        ModelAndView modelAndView = new ModelAndView("mvcdemo");
        // 传递user实体对象给视图
        modelAndView.addObject("user", user);
        return modelAndView;
    }
}
