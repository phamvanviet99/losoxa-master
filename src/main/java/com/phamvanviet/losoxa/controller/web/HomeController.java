package com.phamvanviet.losoxa.controller.web;

import com.phamvanviet.losoxa.entity.Category;
import com.phamvanviet.losoxa.entity.Product;
import com.phamvanviet.losoxa.model.response.ProductResponse;
import com.phamvanviet.losoxa.service.CategoryService;
import com.phamvanviet.losoxa.service.ProductService;
import com.phamvanviet.losoxa.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/home")
public class HomeController {

    private CategoryService categoryService;
    private ProductService productService;

    @Autowired
    public HomeController(CategoryService categoryService, ProductService productService) {
        this.categoryService = categoryService;
        this.productService = productService;
    }

    @GetMapping
    public ModelAndView home(){
        ModelAndView mav = new ModelAndView("/web/index");
        List<Category> listCategory = categoryService.getCategoryPopular();
        mav.addObject("listCategory",listCategory);
        List<ProductResponse> productResponses = productService.getProductPopular();
        mav.addObject("listProduct", productResponses);
        if ( SecurityContextHolder.getContext().getAuthentication() != null && SecurityContextHolder.getContext().getAuthentication().isAuthenticated() && !(SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken)){
            List<ProductResponse> productFavourites = productService.getProductFavourite(SecurityUtils.getPrinciple().getId());
            mav.addObject("productFavourites", productFavourites);
            mav.addObject("username", SecurityUtils.getPrinciple().getUsername());
        }
        return mav;
    }
}
