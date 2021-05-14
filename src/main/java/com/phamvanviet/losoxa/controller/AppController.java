package com.phamvanviet.losoxa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AppController {
    @GetMapping(value = {"/","/index"})
    public String index(){
        return "redirect:/home";
    }

    @GetMapping("/login")
        public ModelAndView login(){
        ModelAndView mav = new ModelAndView("/login");
        return mav;
    }
    @GetMapping(value = "/accessDenied")
    public ModelAndView accessDenied() {
        return new ModelAndView("redirect:/login?accessDenied");
    }




}
