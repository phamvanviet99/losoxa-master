package com.phamvanviet.losoxa.model.request.checkout;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {
    @Size(min = 1, max = 50, message = "Must be between 1 and 50 characters")
    private String fullName;

    @Size(min = 1, max = 100, message = "Must be between 1 and 100 characters")
    private String shippingAddress;

    private String note;

    private Long totalPrice;

    private Integer point;

    private Integer paymentType;

}
