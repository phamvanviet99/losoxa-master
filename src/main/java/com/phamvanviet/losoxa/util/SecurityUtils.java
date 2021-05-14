package com.phamvanviet.losoxa.util;

import com.phamvanviet.losoxa.security.CustomUserDetails;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;
import java.util.List;

public class SecurityUtils {
    public static CustomUserDetails getPrinciple(){
        CustomUserDetails customUserDetails = (CustomUserDetails) (SecurityContextHolder.getContext())
                .getAuthentication().getPrincipal();
        return customUserDetails;
    }


    @SuppressWarnings("unchecked")
    public static List<String> getAuthorities() {
        List<String> results = new ArrayList<>();
        List<GrantedAuthority> authorities = (List<GrantedAuthority>)(SecurityContextHolder.getContext().getAuthentication().getAuthorities());
        for (GrantedAuthority authority : authorities) {
            results.add(authority.getAuthority());
        }
        return results;
    }
}
