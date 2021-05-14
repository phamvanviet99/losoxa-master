package com.phamvanviet.losoxa.controller.admin;

import com.phamvanviet.losoxa.util.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin/report/")
public class ReportController {

    @GetMapping("/list")
    public ModelAndView general() {
        ModelAndView modelAndView =new ModelAndView("admin/report/all");
        modelAndView.addObject("currentRole", SecurityUtils.getPrinciple().getAuthorities());
        return modelAndView;
    }

    @GetMapping("/loyal-customer")
    public ModelAndView loyalCustomer() {
        ModelAndView modelAndView =new ModelAndView("admin/report/user-report");
        modelAndView.addObject("currentRole", SecurityUtils.getPrinciple().getAuthorities());
        return modelAndView;
    }

    @GetMapping("/revenue")
    public ModelAndView productPopular() {
        ModelAndView modelAndView =new ModelAndView("admin/report/revenue-report");
        modelAndView.addObject("currentRole", SecurityUtils.getPrinciple().getAuthorities());
        return modelAndView;
    }

}
