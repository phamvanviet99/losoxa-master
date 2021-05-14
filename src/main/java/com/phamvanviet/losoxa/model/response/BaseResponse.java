package com.phamvanviet.losoxa.model.response;

import lombok.Data;

import java.util.Date;

@Data
public class BaseResponse {
    private Long id;

    private String createdBy;

    private String modifiedBy;

    private Date createdAt;

    private Date modifiedAt;
}
