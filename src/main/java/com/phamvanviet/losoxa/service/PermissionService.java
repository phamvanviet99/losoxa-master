package com.phamvanviet.losoxa.service;

import com.phamvanviet.losoxa.model.request.permission.PermissionRequest;
import com.phamvanviet.losoxa.model.response.PermissionResponse;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;

public interface PermissionService {
    List<PermissionResponse> findAll();

    List<PermissionResponse> findByLevel1();

    List<PermissionResponse> getPagePermission(int offset, int limit);

    int getCountAllPermission();

    PermissionResponse findById(Long id);

    List<PermissionResponse> findPermissionNotId(Long id);

    List<String> convertPermissionToString(Collection<GrantedAuthority> authorities);

    void deletePermission(Long id);

    /*api*/
    PermissionResponse apiCreatePermission(PermissionRequest permissionRequest);

    PermissionResponse apiUpdatePermission(PermissionRequest permissionRequest, Long id);
}
