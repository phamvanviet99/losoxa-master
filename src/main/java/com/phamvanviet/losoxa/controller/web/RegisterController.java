package com.phamvanviet.losoxa.controller.web;

import com.phamvanviet.losoxa.model.request.user.UserRegisterRequest;
import com.phamvanviet.losoxa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ModelAndView register(@ModelAttribute("userRegister") UserRegisterRequest userRegisterRequest) {
        ModelAndView mav = new ModelAndView("web/register");
        return mav;
    }

    @PostMapping
    public String register(@Valid @ModelAttribute("userRegister") UserRegisterRequest userRegisterRequest, BindingResult result, ModelMap modelMap) {
        String username = userRegisterRequest.getUserName();
        if(!userService.usernameValid(username)) {
            result.addError(new FieldError("userRegisterRequest", "userName", "Username này đã tồn tại!"));
        }
        String email = userRegisterRequest.getEmail();
        if(!userService.emailValid(email)) {
            result.addError(new FieldError("userRegisterRequest", "email", "Email này đã tồn tại!"));
        }
        if(result.hasErrors()) {
            return "/web/register";
        }
        if (userService.register(userRegisterRequest)){
            return "redirect:/login";
        }
        return "redirect:/register";
    }

}
