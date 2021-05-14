package com.phamvanviet.losoxa.model.response;

import lombok.Data;

import java.util.List;

@Data
public class CategoryResponse extends BaseResponse {
    private String name;

    private String categoryImage;

    private List<ProductResponse> products;
}
