package com.phamvanviet.losoxa.controller.admin;

import com.phamvanviet.losoxa.service.OrderService;
import com.phamvanviet.losoxa.service.ProductService;
import com.phamvanviet.losoxa.service.UserService;
import com.phamvanviet.losoxa.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/admin/home")
public class AdminHomeController {

    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;
    @Autowired
    private OrderService orderService;

    @GetMapping
    public ModelAndView adminHome() {
        ModelAndView mav = new ModelAndView("/admin/index");
        mav.addObject("currentRole", SecurityUtils.getPrinciple().getAuthorities());
        Map<Integer, Integer> statisticalUser = userService.statistical();
        mav.addObject("statisticalUser", statisticalUser);
        mav.addObject("sumUser", userService.getCount());

        Map<Integer, Integer> statisticalProduct = productService.statistical();
        mav.addObject("statisticalProduct", statisticalProduct);
        mav.addObject("sumProduct", productService.getCount());

        Map<Integer, Integer> statisticalOrder = orderService.statistical();
        mav.addObject("statisticalOrder", statisticalOrder);
        mav.addObject("sumOrder", orderService.getCount());

        Map<Integer, Integer> statisticalRevenue = orderService.statisticalRevenue();
        mav.addObject("statisticalRevenue", statisticalRevenue);
        mav.addObject("sumRevenue", orderService.getCountRevenue());

        return mav;
    }
}
