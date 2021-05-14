package com.phamvanviet.losoxa.model.response;

import lombok.Data;

import java.util.List;

@Data
public class RoleResponse extends BaseResponse{
    private Long id;

    private String name;

    private String description;

    private List<PermissionResponse> permission;
}
