package com.phamvanviet.losoxa.controller.web;

import com.phamvanviet.losoxa.constant.PageConstant;
import com.phamvanviet.losoxa.entity.LineItem;
import com.phamvanviet.losoxa.entity.Order;
import com.phamvanviet.losoxa.model.request.order.OrderUpdateRequest;
import com.phamvanviet.losoxa.model.response.OrderResponse;
import com.phamvanviet.losoxa.model.response.PageResponse;
import com.phamvanviet.losoxa.model.response.ProductResponse;
import com.phamvanviet.losoxa.repository.LineItemRepository;
import com.phamvanviet.losoxa.repository.OrderRepository;
import com.phamvanviet.losoxa.security.CustomUserDetails;
import com.phamvanviet.losoxa.service.OrderService;
import com.phamvanviet.losoxa.service.PageService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

@Controller
public class OrderController {
    private final int limit = PageConstant.LIMIT_ORDER_WEB;
    @Autowired
    private OrderService orderService;
    @Autowired
    private LineItemRepository lineItemRepository;
    @Autowired
    private PageService pageService;

    @GetMapping("/order-history")
    public ModelAndView orderHistoryGet(@AuthenticationPrincipal CustomUserDetails userDetails, @RequestParam(value = "page", defaultValue = "1", required = false) int page) {
        List<OrderResponse> orderResponses = orderService.getOrderAndSortByUserId(userDetails.getId(),(page - 1) * limit, limit);
        PageResponse pageResponse = pageService.pageOrderByUser((page - 1) * limit, limit, page, orderResponses);

        ModelAndView mav;
        if (!orderResponses.isEmpty()) {
            mav = new ModelAndView("/web/order/order-history");
            mav.addObject("orders", orderResponses);
            mav.addObject("model", pageResponse);
        } else {
            mav = new ModelAndView("web/cart/checkout-message");
            mav.addObject("message", "B???n ch??a c?? ????n h??ng n??o");
        }
        return mav;
    }

    @GetMapping("/order/{id}")
    public ModelAndView orderDetailGet(@AuthenticationPrincipal CustomUserDetails userDetails, @PathVariable(value = "id") Long orderId) {
        Order order = orderService.findOrderById(orderId);
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>(userDetails.getAuthorities());
        ModelAndView mav;
        if (order != null && (userDetails.getId() == order.getUser().getId() || isAdmin(grantedAuthorities))) {
            mav = new ModelAndView("/web/order/order-detail");
            OrderResponse orderResponse = new OrderResponse();
            BeanUtils.copyProperties(order, orderResponse);
            orderResponse.setUsername(order.getUser().getUserName());
            orderResponse.setOrderDate(order.getCreatedAt());
            List<LineItem> lineItems = lineItemRepository.findLineItemByOrderId(order.getId());
            orderResponse.setItemList(lineItems);
            mav.addObject("order", orderResponse);
        } else {
            mav = new ModelAndView("web/cart/checkout-message");
            mav.addObject("message", "Kh??ng t???n t???i ????n h??ng n??y ho???c b???n kh??ng c?? quy???n xem");
        }
        return mav;
    }

    @GetMapping("/cancel/{id}")
    public String cancel(@PathVariable final Long id, ModelMap modelMap) {
        if(orderService.cancelOrder(id)) {
            modelMap.addAttribute("message", "H???y ????n h??ng th??nh c??ng");
            return "/web/cart/checkout-message";
        } else {
            modelMap.addAttribute("message", "Hi???n s???n ph???m ??ang giao n??n b???n kh??ng th??? h???y ????n h??ng!");
            return "/web/cart/checkout-message";
        }
    }

    private boolean isAdmin(Set<GrantedAuthority> grantedAuthorities) {
        Predicate<GrantedAuthority> compare = s -> s.getAuthority().equals("ROLE_ADMIN");
        return grantedAuthorities.stream().anyMatch(compare);
    }
}
