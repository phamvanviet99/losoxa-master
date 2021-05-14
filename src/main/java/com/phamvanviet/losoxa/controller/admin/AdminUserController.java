package com.phamvanviet.losoxa.controller.admin;

import com.phamvanviet.losoxa.constant.PageConstant;
import com.phamvanviet.losoxa.entity.Role;
import com.phamvanviet.losoxa.entity.User;
import com.phamvanviet.losoxa.model.request.user.UserCreateRequest;
import com.phamvanviet.losoxa.model.request.user.UserUpdateRequest;
import com.phamvanviet.losoxa.model.response.PageResponse;
import com.phamvanviet.losoxa.model.response.ProductResponse;
import com.phamvanviet.losoxa.model.response.UserResponse;
import com.phamvanviet.losoxa.repository.RoleRepository;
import com.phamvanviet.losoxa.repository.UserRepository;
import com.phamvanviet.losoxa.security.CustomUserDetails;
import com.phamvanviet.losoxa.service.PageService;
import com.phamvanviet.losoxa.service.UserService;
import com.phamvanviet.losoxa.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
@RequestMapping("/admin/user")
public class AdminUserController {
    private final int limit = PageConstant.LIMIT_USER_ADMIN;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PageService pageService;

    @GetMapping
    public ModelAndView user(@RequestParam(value = "page", defaultValue = "1") int page, HttpServletRequest request){
        List<UserResponse> userResponses = userService.getUserAndSort((page - 1) * limit, limit);
        PageResponse pageResponse = pageService.pageAdminUser((page - 1) * limit, limit, page, userResponses);
        ModelAndView mav = new ModelAndView("admin/user/user");
        mav.addObject("model", pageResponse);
        mav.addObject("users", userResponses);
        mav.addObject("message", request.getParameter("message"));
        mav.addObject("currentRole", SecurityUtils.getPrinciple().getAuthorities());
        return mav;
    }

    @GetMapping("/create")
    public ModelAndView create(@ModelAttribute(value = "userRequest") UserCreateRequest userCreateRequest) {
        ModelAndView mav = new ModelAndView("admin/user/user-create");
        List<Role> roles = roleRepository.findAll();
        mav.addObject("roles", roles);
        mav.addObject("currentRole", SecurityUtils.getPrinciple().getAuthorities());
        return mav;
    }

    @PostMapping("/create")
    public String create(@Valid @ModelAttribute(value = "userRequest") UserCreateRequest userCreateRequest, BindingResult result, ModelMap modelMap) {
        String username = userCreateRequest.getUserName();
        if(!userService.usernameValid(username)) {
            result.addError(new FieldError("userCreateRequest", "userName", "Username này đã tồn tại!"));
        }
        String email = userCreateRequest.getEmail();
        if(!userService.emailValid(email)) {
            result.addError(new FieldError("userCreateRequest", "email", "Email này đã tồn tại!"));
        }
        if(result.hasErrors()) {
            List<Role> roles = roleRepository.findAll();
            modelMap.addAttribute("roles", roles);
            return "admin/user/user-create";
        }
        userService.createUser(userCreateRequest);
        return "redirect:/admin/user?message=insert_success";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable(value = "id") final Long id, @ModelAttribute(value = "userRequest") UserUpdateRequest userUpdateRequest, ModelMap modelMap, @AuthenticationPrincipal CustomUserDetails userDetails) {
        if (!id.equals(userDetails.getId())) {
            List<Role> roles = roleRepository.findAll();
            UserResponse user = userService.findUserById(id);
            modelMap.addAttribute("roles", roles);
            modelMap.addAttribute("user", user);
            modelMap.addAttribute("currentRole", SecurityUtils.getPrinciple().getAuthorities());
            return "admin/user/user-update";
        } else {
            modelMap.addAttribute("message", "Tài khoản Admin này đang đăng nhập không thể sửa!");
            modelMap.addAttribute("currentRole", SecurityUtils.getPrinciple().getAuthorities());
            return "admin/message/message";
        }
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable(value = "id") final Long id, @Valid @ModelAttribute(value = "userRequest") UserUpdateRequest userUpdateRequest, BindingResult result, ModelMap modelMap) {
        User user = userRepository.findUserById(id);
        String email = userUpdateRequest.getEmail();
        if(!email.equals(user.getEmail())&&!userService.emailValid(email)) {
            result.addError(new FieldError("userUpdateRequest", "email", "Email này đã tồn tại!"));
        }
        if(result.hasErrors()) {
            List<Role> roles = roleRepository.findAll();
            modelMap.addAttribute("roles", roles);
            modelMap.addAttribute("user", user);
            return "admin/user/user-update";
        }
        userService.updateUser(userUpdateRequest, id);
        return "redirect:/admin/user?message=update_success";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id, @AuthenticationPrincipal CustomUserDetails userDetails, ModelMap modelMap) {
        if (!id.equals(userDetails.getId())) {
            userService.deleteUser(id);
            return "redirect:/admin/user";
        } else {
            modelMap.addAttribute("message", "Tài khoản Admin này đang đăng nhập không thể xóa!");
            return "admin/message/message";
        }
    }
}
