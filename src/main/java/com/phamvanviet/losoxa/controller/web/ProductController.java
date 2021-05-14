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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ProductController {
    private final int limit = PageConstant.LIMIT_PRODUCT_WEB;
    private CategoryService categoryService;
    private ProductService productService;
    private PageService pageService;

    @Autowired
    public ProductController(CategoryService categoryService, ProductService productService, PageService pageService) {
        this.categoryService = categoryService;
        this.productService = productService;
        this.pageService = pageService;
    }

    @GetMapping("/product")
    public ModelAndView listProduct(@RequestParam(value = "page", defaultValue = "1", required = false) int page, @RequestParam(value = "search", required = false) String keyword){
        List<ProductResponse> productResponses = productService.getListProductPopular((page - 1) * limit, limit, keyword);
        PageResponse pageResponse = pageService.pageAdminProduct((page - 1) * limit, limit, page, productResponses);
        ModelAndView mav = new ModelAndView("web/product/product");
        List<Category> listCategory = categoryService.getListProductCategory();
        if ( SecurityContextHolder.getContext().getAuthentication() != null && SecurityContextHolder.getContext().getAuthentication().isAuthenticated() && !(SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken)){
            List<ProductResponse> productFavourites = productService.getProductFavourite(SecurityUtils.getPrinciple().getId());
            mav.addObject("productFavourites", productFavourites);
        }
        mav.addObject("listCategory",listCategory);
        mav.addObject("model", pageResponse);

        return mav;
    }

    @GetMapping("/product/{id}")
    public ModelAndView productDetail(@PathVariable(name = "id") Long id){
        ModelAndView mav = new ModelAndView("web/product/product-detail");
        productService.viewIncrement(id);
        Product product = productService.getProductById(id);
        mav.addObject("product",product);
        List<Product> productRelates = productService.findProductRelate(product.getId(), product.getCategory().getId());
        mav.addObject("productRelates",productRelates);
        List<Category> listCategory = categoryService.getListProductCategory();
        mav.addObject("listCategory",listCategory);
        return mav;
    }

    @GetMapping("/product/price")
    public ModelAndView listProductFilterPrice(@RequestParam(value = "page", defaultValue = "1", required = false) int page,
                                               @RequestParam(value = "min") long min, @RequestParam(value = "max") long max){
        List<ProductResponse> productResponses = productService.getListProductFilterPrice((page - 1) * limit, limit, min, max);
        PageResponse pageResponse = pageService.pageAdminProduct((page - 1) * limit, limit, page, productResponses);
        ModelAndView mav = new ModelAndView("web/product/product");
        List<Category> listCategory = categoryService.getListProductCategory();
        mav.addObject("listCategory",listCategory);
        mav.addObject("model", pageResponse);
        return mav;
    }

    @GetMapping("/product-new")
    public ModelAndView listProductNew(@RequestParam(value = "page", defaultValue = "1", required = false) int page){
        List<ProductResponse> productResponses = productService.getListProduct((page - 1) * limit, limit);
        PageResponse pageResponse = pageService.pageAdminProduct((page - 1) * limit, limit, page, productResponses);
        ModelAndView mav = new ModelAndView("web/product/product");
        List<Category> listCategory = categoryService.getListProductCategory();
        if ( SecurityContextHolder.getContext().getAuthentication() != null && SecurityContextHolder.getContext().getAuthentication().isAuthenticated() && !(SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken)){
            List<ProductResponse> productFavourites = productService.getProductFavourite(SecurityUtils.getPrinciple().getId());
            mav.addObject("productFavourites", productFavourites);
        }
        mav.addObject("listCategory",listCategory);
        mav.addObject("model", pageResponse);

        return mav;
    }
}
