package com.phamvanviet.losoxa.api.admin;

import com.phamvanviet.losoxa.model.request.permission.PermissionRequest;
import com.phamvanviet.losoxa.model.response.PermissionResponse;
import com.phamvanviet.losoxa.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/permission")
public class PermissionAPI {
    @Autowired
    private PermissionService permissionService;

    @PostMapping
    public PermissionResponse create(@RequestBody PermissionRequest permissionRequest){
        return permissionService.apiCreatePermission(permissionRequest);
    }

    @PutMapping("/{id}")
    public PermissionResponse update(@RequestBody PermissionRequest permissionRequest, @PathVariable("id") Long id){
        return permissionService.apiUpdatePermission(permissionRequest, id);
    }
}
