package com.phamvanviet.losoxa.controller.web;

import com.phamvanviet.losoxa.constant.PageConstant;
import com.phamvanviet.losoxa.entity.Category;
import com.phamvanviet.losoxa.entity.Favourite;
import com.phamvanviet.losoxa.entity.Product;
import com.phamvanviet.losoxa.model.response.FavouriteResponse;
import com.phamvanviet.losoxa.model.response.PageResponse;
import com.phamvanviet.losoxa.model.response.ProductResponse;
import com.phamvanviet.losoxa.service.CategoryService;
import com.phamvanviet.losoxa.service.FavouriteService;
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
public class FavouriteController {
    private final int limit = PageConstant.LIMIT_PRODUCT_WEB;
    @Autowired
    private FavouriteService favouriteService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductService productService;
    @Autowired
    private PageService pageService;

    @GetMapping("/favourite")
    public ModelAndView listProduct(@RequestParam(value = "page", defaultValue = "1", required = false) int page){
        List<FavouriteResponse> favourites = favouriteService.getListFavourite(SecurityUtils.getPrinciple().getId(),  (page - 1) * limit, limit);
        PageResponse pageResponse = pageService.pageFavouriteProduct((page - 1) * limit, limit, page, favourites);
        ModelAndView mav = new ModelAndView("/web/favourite/favourite");
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
