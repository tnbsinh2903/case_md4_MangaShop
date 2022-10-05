package com.cg.controller;

import com.cg.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    AppUtils appUtils;

    @GetMapping
    public ModelAndView listPro(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user/list");
        String userName = appUtils.getPrincipal();
        modelAndView.addObject("user", userName);

        return modelAndView;
    }
}
