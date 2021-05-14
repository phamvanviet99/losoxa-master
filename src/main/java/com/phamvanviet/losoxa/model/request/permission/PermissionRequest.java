package com.phamvanviet.losoxa.model.request.permission;

import lombok.Data;

@Data
public class PermissionRequest {
    private String name;

    private String description;

    private Long parentId;
}
