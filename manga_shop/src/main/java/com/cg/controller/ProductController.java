package com.cg.controller;

import com.cg.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    AppUtils appUtils;

    @GetMapping
    public ModelAndView listPro(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("product/list");
        String username = appUtils.getPrincipal();
        modelAndView.addObject("username", username);
        return modelAndView;
    }
}

