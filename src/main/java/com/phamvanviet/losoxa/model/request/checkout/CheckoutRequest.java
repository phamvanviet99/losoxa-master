package com.phamvanviet.losoxa.model.request.checkout;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CheckoutRequest {
    private OrderRequest orderRequest;
    private List<LineItemRequest> lineItemRequests;
}
