package com.phamvanviet.losoxa.model.request.rate;

import lombok.Data;

@Data
public class RateRequest {
    private Integer star;
    private String content;
    private Long productId;
    private Long userId;

}
