package com.phamvanviet.losoxa.model.request.role;

import lombok.Data;

import java.util.List;

@Data
public class RoleRequest {
    private String name;

    private String description;

    private List<Long> permissionIds;
}
