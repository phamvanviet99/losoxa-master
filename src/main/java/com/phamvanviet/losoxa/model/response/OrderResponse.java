package com.phamvanviet.losoxa.model.response;

import com.phamvanviet.losoxa.entity.LineItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {

    private Long id;

    private String username;

    private Date orderDate;

    private String note;

    private String shippingAddress;

    private Long totalPrice;

    private String status;

    private Integer point;

    private Integer paymentType;

    private List<LineItem> itemList;

    private List<String> listStatus;
}
