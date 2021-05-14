package com.phamvanviet.losoxa.controller.web;

import com.phamvanviet.losoxa.constant.PageConstant;
import com.phamvanviet.losoxa.entity.Category;
import com.phamvanviet.losoxa.entity.Product;
import com.phamvanviet.losoxa.model.response.PageResponse;
import com.phamvanviet.losoxa.model.response.ProductResponse;
import com.phamvanviet.losoxa.service.CategoryService;
import com.phamvanviet.losoxa.service.PageService;
import com.phamvanviet.losoxa.service.ProductService;
import com.phamvanviet.losoxa.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class CategoryController {
    private final int limit = PageConstant.LIMIT_CATEGORY_WEB;
    private CategoryService categoryService;
    private ProductService productService;
    private PageService pageService;

    @Autowired
    public CategoryController(CategoryService categoryService, ProductService productService, PageService pageService) {
        this.categoryService = categoryService;
        this.productService = productService;
        this.pageService = pageService;
    }

//    @GetMapping("/category")
//    public ModelAndView listProduct(@RequestParam(value = "page", defaultValue = "1", required = false) int page,
//                                    @RequestParam(value = "categoryId" ,defaultValue = "1",required = false) long categoryId){
//        List<ProductResponse> productResponses = productService.getListProductByCategoryId(categoryId, (page - 1) * limit, limit);
//        PageResponse pageResponse = pageService.pageAdminProduct((page - 1) * limit, limit, page, productResponses);
//        ModelAndView mav = new ModelAndView("web/category/category");
//        List<Category> listCategory = categoryService.getListProductCategory();
//        if ( SecurityContextHolder.getContext().getAuthentication() != null && SecurityContextHolder.getContext().getAuthentication().isAuthenticated() && !(SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken)){
//            List<ProductResponse> productFavourites = productService.getProductFavourite(SecurityUtils.getPrinciple().getId());
//            mav.addObject("productFavourites", productFavourites);
//        }
//        mav.addObject("listCategory",listCategory);
//        mav.addObject("model", pageResponse);
//        mav.addObject("categoryId", categoryId);
//        return mav;
//    }

    @GetMapping("/category")
    public ModelAndView listProduct(@RequestParam(value = "page", defaultValue = "1", required = false) int page,
                                    @RequestParam(value = "categoryId" ,defaultValue = "1",required = false) long categoryId){
        List<ProductResponse> productResponses = productService.getListProductPopularByCategoryId(categoryId, (page - 1) * limit, limit);
        PageResponse pageResponse = pageService.pageAdminProduct((page - 1) * limit, limit, page, productResponses);
        ModelAndView mav = new ModelAndView("web/category/category");
        List<Category> listCategory = categoryService.getListProductCategory();
        if ( SecurityContextHolder.getContext().getAuthentication() != null && SecurityContextHolder.getContext().getAuthentication().isAuthenticated() && !(SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken)){
            List<ProductResponse> productFavourites = productService.getProductFavourite(SecurityUtils.getPrinciple().getId());
            mav.addObject("productFavourites", productFavourites);
        }
        mav.addObject("listCategory",listCategory);
        mav.addObject("model", pageResponse);
        mav.addObject("categoryId", categoryId);
        return mav;
    }
}
