package com.phamvanviet.losoxa.model.request.favourite;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FavouriteRequest {
    private Long userId;

    private Long productId;
}
