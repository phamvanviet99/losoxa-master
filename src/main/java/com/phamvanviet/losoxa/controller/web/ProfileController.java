package com.phamvanviet.losoxa.controller.web;

import com.phamvanviet.losoxa.entity.User;
import com.phamvanviet.losoxa.model.request.profile.ProfileRequest;
import com.phamvanviet.losoxa.model.response.UserResponse;
import com.phamvanviet.losoxa.repository.UserRepository;
import com.phamvanviet.losoxa.security.CustomUserDetails;
import com.phamvanviet.losoxa.service.ProfileService;
import com.phamvanviet.losoxa.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

@Controller
public class ProfileController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ProfileService profileService;


    @GetMapping("profile")
    public ModelAndView profile(@ModelAttribute(value = "profile") ProfileRequest profileRequest, @AuthenticationPrincipal CustomUserDetails userDetails) {
        ModelAndView mav = new ModelAndView("web/profile/profile");
        UserResponse user = userService.findUserById(userDetails.getId());
        mav.addObject("user", user);
        mav.addObject("message", "Nhập vào thông tin để thay đổi thông tin cá nhân");
        return mav;
    }

    @PostMapping("profile")
    public String editProfilePost(@Valid @ModelAttribute(value = "profile") ProfileRequest profileRequest, BindingResult result, ModelMap modelMap, @AuthenticationPrincipal CustomUserDetails userDetails) {
        User user = userRepository.findUserById(userDetails.getId());
        UserResponse userResponse = new UserResponse();
        BeanUtils.copyProperties(user, userResponse);

        if(profileRequest.getCurrentPassword() != "" || profileRequest.getNewPassword() != "" || profileRequest.getConfirmPassword() != ""){
            if(!profileRequest.getNewPassword().equals(profileRequest.getConfirmPassword()))
                result.addError(new FieldError("profileRequest", "confirmPassword", "Mật khẩu không trùng khớp"));

            if (!passwordEncoder.matches(profileRequest.getCurrentPassword(), user.getPassword()))
                result.addError(new FieldError("profileRequest", "currentPassword", "Mật khẩu cũ không đúng"));

            user.setPassword(passwordEncoder.encode(profileRequest.getNewPassword()));

        }
        if (result.hasErrors()) {
            modelMap.addAttribute("user", userResponse);
            modelMap.addAttribute("message", "Dữ liệu nhập vào chưa đúng, mời bạn nhập lại");
            return "web/profile/profile";
        } else {
            profileService.updateProfile(userDetails.getId(), profileRequest.getFullName(), profileRequest.getPhone(), profileRequest.getAddress());
        }

        modelMap.addAttribute("message", "Đổi thông tin cá nhân thành công");

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>(userDetails
                .getAuthorities());

        if (isAdmin(grantedAuthorities)) {
            return "/admin/message/message";
        } else {
            return "web/cart/checkout-message";
        }
    }

    private boolean isAdmin(Set<GrantedAuthority> grantedAuthorities) {
        Predicate<GrantedAuthority> compare = s -> s.getAuthority().equals("ROLE_ADMIN");
        return grantedAuthorities.stream().anyMatch(compare);
    }
}
