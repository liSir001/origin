package com.lisir.cn.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Auther: liSir
 */
@RestController
public class HelloController {
    @GetMapping
    public ModelAndView hello(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);
        return new ModelAndView("index","helloModel", model);
    }
}
