package com.phamvanviet.losoxa.model.response;

import com.phamvanviet.losoxa.entity.Category;
import lombok.Data;

@Data
public class ProductResponse extends BaseResponse {
    private String name;

    private Long unitPrice;

    private Integer quantity;

    private String productImage;

    private String description;

    private Integer views;

    private Integer quantitySold;

    private Category category;
}
