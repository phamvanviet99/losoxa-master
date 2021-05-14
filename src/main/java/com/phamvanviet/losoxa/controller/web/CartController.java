package com.phamvanviet.losoxa.controller.web;

import com.phamvanviet.losoxa.entity.Category;
import com.phamvanviet.losoxa.model.request.checkout.CheckoutRequest;
import com.phamvanviet.losoxa.model.request.checkout.OrderRequest;
import com.phamvanviet.losoxa.service.CategoryService;
import com.phamvanviet.losoxa.service.OrderService;
import com.phamvanviet.losoxa.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class CartController {
    private CategoryService categoryService;
    private OrderService orderService;

    @Autowired
    public CartController(CategoryService categoryService, OrderService orderService) {
        this.categoryService = categoryService;
        this.orderService = orderService;
    }

    @GetMapping("/cart")
    public ModelAndView shoppingCart() {
        ModelAndView mav = new ModelAndView("web/cart/cart");
        List<Category> listCategory = categoryService.getListProductCategory();
        mav.addObject("listCategory", listCategory);
        return mav;
    }

    @GetMapping("/checkout")
    public ModelAndView checkout(@ModelAttribute(value = "orderRequest") OrderRequest orderRequest) {
        ModelAndView mav = new ModelAndView("web/cart/checkout");
        return mav;
    }

    @PostMapping("/checkout")
    @ResponseBody
    public String checkout(@RequestBody CheckoutRequest checkoutRequest) {
        if (checkoutRequest.getLineItemRequests() != null) {
            if (checkoutRequest.getLineItemRequests().size() > 0) {
                orderService.saveOrder(SecurityUtils.getPrinciple().getId(), checkoutRequest.getOrderRequest(), checkoutRequest.getLineItemRequests());
                return "/web/checkout-message";
            }
        }
        return "/web/checkout";
    }

    @GetMapping("/checkout-message")
    public ModelAndView orderResultGet(@RequestParam(value = "error", defaultValue = "true") String error, @ModelAttribute(value = "checkoutRequest") CheckoutRequest checkoutRequest) {
        ModelAndView mav = new ModelAndView("web/cart/checkout-message");
        if (error.equalsIgnoreCase("false")) {
//            orderService.saveOrder(SecurityUtils.getPrinciple().getId(), checkoutRequest.getOrderRequest(), checkoutRequest.getLineItemRequests());
            mav.addObject("message", "Đặt hàng thành công");
        } else
            mav.addObject("message", "Đặt hàng thất bại. Bạn chưa có sản phẩm nào trong giỏ");
        return mav;
    }


//    @PostMapping("/checkout")
//    @ResponseBody
//    public ResponseEntity<?> submitOrderPost(@AuthenticationPrincipal CustomUserDetails userDetails, @RequestBody CheckoutRequest checkoutRequest) {
//        if (checkoutRequest.getLineItemRequests() != null)
//            if (checkoutRequest.getLineItemRequests().size() > 0) {
//                orderService.saveOrder(userDetails.getId(), checkoutRequest.getOrderRequest(), checkoutRequest.getLineItemRequests());
//                return ResponseEntity.status(200).build();
//            }
//        return ResponseEntity.status(400).build();
//    }
}
