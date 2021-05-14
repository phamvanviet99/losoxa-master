package com.phamvanviet.losoxa.controller.web;

import javax.servlet.http.HttpServletRequest;

import com.phamvanviet.losoxa.service.OrderService;
import com.phamvanviet.losoxa.util.PaypalUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import com.phamvanviet.losoxa.config.paypal.PaypalPaymentIntent;
import com.phamvanviet.losoxa.config.paypal.PaypalPaymentMethod;
import com.phamvanviet.losoxa.service.PaypalService;
import org.springframework.web.servlet.ModelAndView;

import java.util.Base64;


@Controller
public class PaymentController {
    public static final String URL_PAYPAL_SUCCESS = "pay/success";
    public static final String URL_PAYPAL_CANCEL = "pay/cancel";

    private Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private PaypalService paypalService;
    @Autowired
    private OrderService orderService;

//    @PostMapping("/payload")
//    public ModelAndView payload(@RequestBody CheckoutRequest checkoutRequest, ModelMap model){
//        model.addAttribute("checkoutRequest", checkoutRequest);
////        ModelAndView mav = new ModelAndView("/web/pay/pay");
////        mav.addObject("totalPrice", paypalService.convertToUSD(checkoutRequest.getOrderRequest().getTotalPrice()));
//        return new ModelAndView("redirect:/pay-view", model);
//    }
//
    @RequestMapping(value = "/pay-view", method = RequestMethod.GET)
    public ModelAndView payView() {
        ModelAndView mav = new ModelAndView("/web/pay/pay");
        return mav;
    }

    @PostMapping("/pay")
    public String pay(HttpServletRequest request, @RequestParam(value = "totalPrice") Long totalPrice){
        String cancelUrl = PaypalUtils.getBaseURL(request) + "/" + URL_PAYPAL_CANCEL;
        String successUrl = PaypalUtils.getBaseURL(request) + "/" + URL_PAYPAL_SUCCESS;
        Double payPrice = paypalService.convertToUSD(totalPrice);
        try {
            Payment payment = paypalService.createPayment(
                    payPrice,
                    "USD",
                    PaypalPaymentMethod.paypal,
                    PaypalPaymentIntent.sale,
                    "payment description",
                    cancelUrl,
                    successUrl);
            for(Links links : payment.getLinks()){
                if(links.getRel().equals("approval_url")){
                    return "redirect:" + links.getHref();
                }
            }
        } catch (PayPalRESTException e) {
            log.error(e.getMessage());
        }
        return "redirect:/";
    }

    @GetMapping(URL_PAYPAL_CANCEL)
    public ModelAndView cancelPay(){
        ModelAndView mav = new ModelAndView("web/pay/message");
        mav.addObject("message", "Đặt hàng thất bại.");
        return mav;
    }

    @GetMapping(URL_PAYPAL_SUCCESS)
    public ModelAndView successPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId){
        ModelAndView mav = new ModelAndView("web/pay/message");
        try {
            Payment payment = paypalService.executePayment(paymentId, payerId);
            if(payment.getState().equals("approved")){
                return mav;
            }
        } catch (PayPalRESTException e) {
            log.error(e.getMessage());
        }
        mav.addObject("message", "Đặt hàng thất bại.");
        return mav;
    }


}
