package com.phamvanviet.losoxa.controller.admin;

import com.phamvanviet.losoxa.constant.PageConstant;
import com.phamvanviet.losoxa.model.request.blog.BlogRequest;
import com.phamvanviet.losoxa.model.response.BlogResponse;
import com.phamvanviet.losoxa.model.response.PageResponse;
import com.phamvanviet.losoxa.service.BlogService;
import com.phamvanviet.losoxa.service.PageService;
import com.phamvanviet.losoxa.service.PermissionService;
import com.phamvanviet.losoxa.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/admin/blog")
public class AdminBlogController {
    private final int limit = PageConstant.LIMIT_BLOG_ADMIN;

    @Autowired
    private PageService pageService;
    @Autowired
    private BlogService blogService;
    @Autowired
    private PermissionService permissionService;

    @GetMapping
    public ModelAndView getListBlog(@RequestParam(value = "page", defaultValue = "1") int page, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("/admin/blog/blog");
        List<BlogResponse> blogResponses = blogService.getPageBlog((page - 1) * limit, limit);
        PageResponse pageResponse = pageService.pageAdminBlog((page - 1) * limit, limit, page, blogResponses);
        mav.addObject("blog", blogResponses);
        mav.addObject("model", pageResponse);
        mav.addObject("message", request.getParameter("message"));
        mav.addObject("currentRole", permissionService.convertPermissionToString(SecurityUtils.getPrinciple().getAuthorities()));
        return mav;
    }

    @GetMapping("/create")
    public ModelAndView getCreateBlog(@Valid @ModelAttribute(value = "blogRequest") BlogRequest blogRequest) {
        ModelAndView mav = new ModelAndView("/admin/blog/create-blog");
        mav.addObject("currentRole", permissionService.convertPermissionToString(SecurityUtils.getPrinciple().getAuthorities()));
        return mav;
    }


    @GetMapping("/update/{id}")
    public ModelAndView getUpdateBlog(@PathVariable(value = "id") Long id, @Valid @ModelAttribute(value = "blogRequest") BlogRequest blogRequest) {
        ModelAndView mav = new ModelAndView("/admin/blog/update-blog");
        BlogResponse blogResponse = blogService.findById(id);
        mav.addObject("blogResponse", blogResponse);
        mav.addObject("currentRole", permissionService.convertPermissionToString(SecurityUtils.getPrinciple().getAuthorities()));
        return mav;
    }


    @GetMapping("delete/{id}")
    public String delete(@PathVariable Long id) {
        blogService.deleteBlog(id);
        return "redirect:/admin/blog";
    }
}
