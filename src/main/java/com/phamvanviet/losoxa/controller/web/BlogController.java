package com.phamvanviet.losoxa.controller.web;

import com.phamvanviet.losoxa.constant.PageConstant;
import com.phamvanviet.losoxa.entity.Category;
import com.phamvanviet.losoxa.model.response.BlogResponse;
import com.phamvanviet.losoxa.model.response.PageResponse;
import com.phamvanviet.losoxa.model.response.ProductResponse;
import com.phamvanviet.losoxa.service.BlogService;
import com.phamvanviet.losoxa.service.PageService;
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
public class BlogController {
    private final int limit = PageConstant.LIMIT_BLOG_WEB;
    @Autowired
    private BlogService blogService;
    @Autowired
    private PageService pageService;

    @GetMapping("/blog")
    public ModelAndView blog(@RequestParam(value = "page", defaultValue = "1", required = false) int page){
        ModelAndView mav = new ModelAndView("/web/blog/blog");
        List<BlogResponse> blogResponses = blogService.getBlogAndSort((page - 1) * limit, limit);
        PageResponse pageResponse = pageService.pageAdminBlog((page - 1) * limit, limit, page, blogResponses);
        mav.addObject("model", pageResponse);
        return mav;
    }

    @GetMapping("/blog/{id}")
    public ModelAndView showNewsDetail(@PathVariable("id") Long id){
        ModelAndView mav = new ModelAndView("/web/blog/blog-detail");
        blogService.viewIncrement(id);
        BlogResponse blogResponse = blogService.findById(id);
        mav.addObject("blog",blogResponse);
        List<BlogResponse> blogResponses = blogService.findBlogTopView(id);
        mav.addObject("blogTop",blogResponses);

        return mav;
    }
}
