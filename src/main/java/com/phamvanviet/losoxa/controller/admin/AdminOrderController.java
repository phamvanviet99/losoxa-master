package com.phamvanviet.losoxa.controller.admin;

import com.phamvanviet.losoxa.constant.PageConstant;
import com.phamvanviet.losoxa.model.request.order.OrderUpdateRequest;
import com.phamvanviet.losoxa.model.response.OrderResponse;
import com.phamvanviet.losoxa.model.response.PageResponse;
import com.phamvanviet.losoxa.repository.LineItemRepository;
import com.phamvanviet.losoxa.repository.OrderRepository;
import com.phamvanviet.losoxa.service.OrderService;
import com.phamvanviet.losoxa.service.PageService;
import com.phamvanviet.losoxa.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin/order")
public class AdminOrderController {
    private final int limit = PageConstant.LIMIT_ORDER_ADMIN;

    @Autowired
    private OrderService orderService;
    @Autowired
    private PageService pageService;

    @GetMapping
    public ModelAndView listOrder(@RequestParam(value = "page", defaultValue = "1", required = false) int page, HttpServletRequest request) {
        List<OrderResponse> orderResponses = orderService.getOrderAndSort((page - 1) * limit, limit);
        PageResponse pageResponse = pageService.pageAdminOrder((page - 1) * limit, limit, page, orderResponses);
        ModelAndView mav = new ModelAndView("/admin/order/order");
        mav.addObject("model", pageResponse);
        mav.addObject("orders", orderResponses);
        mav.addObject("message", request.getParameter("message"));
        mav.addObject("currentRole", SecurityUtils.getPrinciple().getAuthorities());
        return mav;
    }

    @GetMapping("/update/{id}")
    public ModelAndView update(@PathVariable final Long id, @ModelAttribute(value = "orderUpdateRequest") OrderUpdateRequest orderUpdateRequest) {
        OrderResponse orderResponse = orderService.getOrderById(id);
        ModelAndView mav;
        if (orderResponse != null) {
            mav = new ModelAndView("/admin/order/order-update");
            mav.addObject("order", orderResponse);
            List<String> listStatus = new ArrayList<>();
            listStatus.add("Ch??? x??c nh???n");
            listStatus.add("Ch??? l???y h??ng");
            listStatus.add("??ang giao");
            listStatus.add("???? giao");
            listStatus.add("Ch??? h???y");
            listStatus.add("???? h???y");
            mav.addObject("listStatus", listStatus);
            mav.addObject("currentRole", SecurityUtils.getPrinciple().getAuthorities());
        } else {
            mav = new ModelAndView("/admin/message/message");
            mav.addObject("message", "Kh??ng t??m th???y ????n h??ng n??y");
            mav.addObject("currentRole", SecurityUtils.getPrinciple().getAuthorities());
        }
        return mav;
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable final Long id, @Valid @ModelAttribute(value = "orderUpdateRequest") OrderUpdateRequest orderUpdateRequest, BindingResult result, ModelMap modelMap) {
        if(result.hasErrors()) {
            List<String> listStatus = new ArrayList<>();
            listStatus.add("Ch??? x??c nh???n");
            listStatus.add("Ch??? l???y h??ng");
            listStatus.add("??ang giao");
            listStatus.add("???? giao");
            listStatus.add("Ch??? h???y");
            listStatus.add("???? h???y");
            modelMap.addAttribute("listStatus", listStatus);
            return "/admin/order/order-update";
        }
        if(orderService.updateOrderStatus(id, orderUpdateRequest.getStatus())) {
            return "redirect:/admin/order?message=update_success";
        } else {
            modelMap.addAttribute("message", "C?? s???n ph???m ???? h???t h??ng, c???n nh???p th??m tr?????c khi x??c nh???n ????n h??ng n??y ???? nh???n.");
            return "/admin/message/message";
        }
    }
}
