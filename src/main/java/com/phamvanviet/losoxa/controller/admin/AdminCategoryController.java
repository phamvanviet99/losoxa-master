package com.phamvanviet.losoxa.controller.admin;

import com.phamvanviet.losoxa.constant.PageConstant;
import com.phamvanviet.losoxa.entity.Category;
import com.phamvanviet.losoxa.model.request.category.CategoryRequest;
import com.phamvanviet.losoxa.model.response.CategoryResponse;
import com.phamvanviet.losoxa.model.response.PageResponse;
import com.phamvanviet.losoxa.model.response.ProductResponse;
import com.phamvanviet.losoxa.repository.CategoryRepository;
import com.phamvanviet.losoxa.service.CategoryService;
import com.phamvanviet.losoxa.service.PageService;
import com.phamvanviet.losoxa.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/admin/category")
public class AdminCategoryController {
    private final int limit = PageConstant.LIMIT_CATEGORY_ADMIN;

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private PageService pageService;

    @GetMapping
    public ModelAndView listCategory(@RequestParam(value = "page", defaultValue = "1") int page, HttpServletRequest request) {
        List<CategoryResponse> categories = categoryService.getCategoryAndSort((page - 1) * limit, limit);
        PageResponse pageResponse = pageService.pageAdminCategory((page - 1) * limit, limit, page, categories);
        ModelAndView mav = new ModelAndView("/admin/category/category");
        mav.addObject("model", pageResponse);
        mav.addObject("categories", categories);
        mav.addObject("message", request.getParameter("message"));
        mav.addObject("currentRole", SecurityUtils.getPrinciple().getAuthorities());
        return mav;
    }

    @GetMapping("/create")
    public ModelAndView create(@ModelAttribute(value = "categoryRequest") CategoryRequest categoryRequest) {
        ModelAndView mav = new ModelAndView("/admin/category/category-create");
        mav.addObject("currentRole", SecurityUtils.getPrinciple().getAuthorities());
        return mav;
    }

    @PostMapping("/create")
    public String create(@Valid @ModelAttribute(value = "categoryRequest") CategoryRequest categoryRequest, BindingResult result, ModelMap modelMap) {
        String name = categoryRequest.getName();
        if(!categoryService.nameValid(name)) {
            result.addError(new FieldError("categoryRequest", "name", "Category name already exist"));
        }
        if(result.hasErrors()) {
            return "/admin/category/category-create";
        }
        categoryService.create(categoryRequest);
        return "redirect:/admin/category?message=insert_success";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable(value = "id") final Long id, @ModelAttribute(value = "categoryRequest") CategoryRequest categoryRequest, ModelMap modelMap) {
        Category category = categoryRepository.findCategoryById(id);
        modelMap.addAttribute("category", category);
        modelMap.addAttribute("currentRole", SecurityUtils.getPrinciple().getAuthorities());
        return "/admin/category/category-update";
    }

    @PostMapping("/update/{id}")
    public String update(@Valid @PathVariable(value = "id") final Long id, @ModelAttribute(value = "categoryRequest") CategoryRequest categoryRequest, BindingResult result, ModelMap modelMap) {
        Category category = categoryRepository.findCategoryById(id);
        String name = categoryRequest.getName();
        if(!category.getName().equals(name) && !categoryService.nameValid(name)) {
            result.addError(new FieldError("categoryRequest", "name", "Category name already exist"));
        }
        if(result.hasErrors()) {
            modelMap.addAttribute("category", category);
            return "/admin/category/category-update";
        }
        categoryService.update(id, categoryRequest);
        return "redirect:/admin/category?message=update_success";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        categoryService.delete(id);
        return "redirect:/admin/category";
    }

}
