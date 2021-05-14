package com.phamvanviet.losoxa.api.admin;

import com.phamvanviet.losoxa.model.request.role.RoleRequest;
import com.phamvanviet.losoxa.model.response.RoleResponse;
import com.phamvanviet.losoxa.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/role")
public class RoleAPI {
    @Autowired
    private RoleService roleService;

    @PostMapping
    public RoleResponse create(@RequestBody RoleRequest roleRequest){
        return roleService.apiCreateRole(roleRequest);
    }

    @PutMapping("/{id}")
    public RoleResponse update(@RequestBody RoleRequest roleRequest, @PathVariable("id") Long id){
        return roleService.apiUpdateRole(roleRequest, id);
    }
}
