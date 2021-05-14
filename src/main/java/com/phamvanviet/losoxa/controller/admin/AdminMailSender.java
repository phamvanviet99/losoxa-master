package com.phamvanviet.losoxa.controller.admin;

import com.phamvanviet.losoxa.model.request.mail.MailGivePasswordRequest;
import com.phamvanviet.losoxa.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class AdminMailSender {
    @Autowired
    private MailService emailService;

    @GetMapping(value = "/forgotPassword")
    public ModelAndView forgot(@Valid @ModelAttribute("mailRequest") MailGivePasswordRequest mailGivePasswordRequest){
        ModelAndView mav = new ModelAndView("web/forgot-password");
        return mav;
    }

    @PostMapping(value = "/forgotPassword")
    public String sendmail(@Valid @ModelAttribute("mailRequest") MailGivePasswordRequest mailGivePasswordRequest) {
        String mailTo = mailGivePasswordRequest.getEmail();
        emailService.sendEmail(mailTo, "Cấp lại mật khẩu Losoxa");

        return "redirect:/login";
    }
}
