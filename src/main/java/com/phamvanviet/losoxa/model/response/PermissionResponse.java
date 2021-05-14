package com.phamvanviet.losoxa.model.response;

import lombok.Data;

import java.util.List;

@Data
public class PermissionResponse extends BaseResponse {
    private Long id;

    private String name;

    private String description;

    private Long parentId;

    private List<PermissionResponse> children;
}
