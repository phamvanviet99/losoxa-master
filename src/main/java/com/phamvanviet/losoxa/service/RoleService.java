package com.phamvanviet.losoxa.service;

import com.phamvanviet.losoxa.entity.Role;
import com.phamvanviet.losoxa.model.request.role.RoleRequest;
import com.phamvanviet.losoxa.model.response.RoleResponse;

import java.util.List;

public interface RoleService {

    List<Role> findAll();

    List<RoleResponse> getPageRole(int offset, int limit);

    int getCountAllRole();

    RoleResponse findById(Long id);

    void deleteRole(Long id);

    List<String> getPermissionNameByRole(Long id);

    /*api*/

    RoleResponse apiCreateRole(RoleRequest roleRequest);

    RoleResponse apiUpdateRole(RoleRequest roleRequest, Long id);
}
