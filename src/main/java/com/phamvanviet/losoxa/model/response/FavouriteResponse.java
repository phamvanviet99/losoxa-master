package com.phamvanviet.losoxa.model.response;

import lombok.Data;

@Data
public class FavouriteResponse {
    private Long id;

    private ProductResponse product;

    private UserResponse user;
}
