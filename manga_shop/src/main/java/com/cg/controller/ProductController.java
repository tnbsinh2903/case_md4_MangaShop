package com.cg.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/products")
public class ProductController {

    @GetMapping
    public ModelAndView listPro(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("product/list");
        return modelAndView;
    }
}

