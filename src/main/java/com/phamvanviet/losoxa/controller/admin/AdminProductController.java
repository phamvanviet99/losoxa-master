package com.phamvanviet.losoxa.controller.admin;

import com.phamvanviet.losoxa.constant.PageConstant;
import com.phamvanviet.losoxa.entity.Category;
import com.phamvanviet.losoxa.entity.Product;
import com.phamvanviet.losoxa.model.request.product.ProductRequest;
import com.phamvanviet.losoxa.model.response.PageResponse;
import com.phamvanviet.losoxa.model.response.ProductResponse;
import com.phamvanviet.losoxa.repository.CategoryRepository;
import com.phamvanviet.losoxa.repository.ProductRepository;
import com.phamvanviet.losoxa.service.PageService;
import com.phamvanviet.losoxa.service.ProductService;
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
@RequestMapping("/admin/product")
public class AdminProductController {

    private final int limit = PageConstant.LIMIT_PRODUCT_ADMIN;

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private PageService pageService;

    @GetMapping
    public ModelAndView productList(@RequestParam(value = "page", defaultValue = "1") int page, HttpServletRequest request){
        List<ProductResponse> productResponses = productService.getProductAndSort((page - 1) * limit, limit);
        PageResponse pageResponse = pageService.pageAdminProduct((page - 1) * limit, limit, page, productResponses);
        ModelAndView mav = new ModelAndView("admin/product/product");
        mav.addObject("model", pageResponse);
        mav.addObject("products", productResponses);
        mav.addObject("message", request.getParameter("message"));
        mav.addObject("currentRole", SecurityUtils.getPrinciple().getAuthorities());
        return mav;
    }

    @GetMapping("/create")
    public ModelAndView create(@ModelAttribute(value = "productRequest") ProductRequest productRequest) {
        ModelAndView mav = new ModelAndView("/admin/product/product-create");
        List<Category> categories = categoryRepository.findAll();
        mav.addObject("categories", categories);
        mav.addObject("currentRole", SecurityUtils.getPrinciple().getAuthorities());
        return mav;
    }

    @PostMapping("/create")
    public String create(@Valid @ModelAttribute(value = "productRequest") ProductRequest productRequest, BindingResult result, ModelMap modelMap) {
        String name = productRequest.getName();
        if(!productService.nameValid(name)) {
            result.addError(new FieldError("productRequest", "name", "Tên sản phẩm đã tồn tại"));
        }
        if (result.hasErrors()){
            List<Category> categories = categoryRepository.findAll();
            modelMap.addAttribute("categories", categories);
            return "/admin/product/product-create";
        }
        productService.create(productRequest);
        return "redirect:/admin/product?message=insert_success";
    }

    @GetMapping("/update/{id}")
    public ModelAndView update(@PathVariable(value = "id") final Long id, @ModelAttribute(value = "productRequest") ProductRequest updateProductRequest, @RequestParam(value = "page", defaultValue = "1") int page, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("/admin/product/product-update");
        Product product = productService.getProductById(id);
        if (product != null) {
            List<Category> categories = categoryRepository.findAll();
            mav.addObject("categories", categories);
            mav.addObject("product", product);
            mav.addObject("currentRole", SecurityUtils.getPrinciple().getAuthorities());
            return mav;
        }
        return productList(page, request);
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable(value = "id") final Long id, @Valid @ModelAttribute(value = "productRequest") ProductRequest updateProductRequest, BindingResult result, HttpServletRequest request, ModelMap modelMap) {
        Product product = productService.getProductById(id);
        String name = updateProductRequest.getName();
        if(!name.equals(product.getName()) && !productService.nameValid(name)) {
            result.addError(new FieldError("updateProductRequest", "name", "Tên sản phẩm đã tồn tại"));
        }
        if(result.hasErrors()) {
            List<Category> categories = categoryRepository.findAll();
            modelMap.addAttribute("categories", categories);
            modelMap.addAttribute("product", product);
            return "/admin/product/product-update";
        }
        productService.update(id, updateProductRequest);
        return "redirect:/admin/product?message=update_success";
    }

    @GetMapping("delete/{id}")
    public String delete(@PathVariable Long id, HttpServletRequest request, ModelMap modelMap) {
        Product product = productService.getProductById(id);
        if (productService.deleteProduct(product)) {
            return "redirect:/admin/product";
        } else {
            modelMap.addAttribute("message", "Sản phẩm đã tồn tại trong hóa đơn, không thể xóa");
            return "admin/message/message";
        }
    }

}
