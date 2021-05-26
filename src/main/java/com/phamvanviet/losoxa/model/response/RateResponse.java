package com.phamvanviet.losoxa.model.response;

import lombok.Data;

@Data
public class RateResponse extends BaseResponse{
    private Integer star;
    private String content;
    private String userName;
}
