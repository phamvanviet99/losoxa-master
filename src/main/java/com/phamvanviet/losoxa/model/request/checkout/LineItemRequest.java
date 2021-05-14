package com.phamvanviet.losoxa.model.request.checkout;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LineItemRequest {
    private Long productId;
    private Long unitPrice;
    private int quantity;
}
