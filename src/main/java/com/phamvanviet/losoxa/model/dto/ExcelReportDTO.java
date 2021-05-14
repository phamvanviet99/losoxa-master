package com.phamvanviet.losoxa.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExcelReportDTO {
    private String userName;
    private String fullName;
    private String phone;
    private String email;
    private int countOrder;
    private Long totalPrice;

    private String productName;
    private Long unitPrice;
    private int quantitySold;
    private Long realRevenue;
    private int point;
    private Long cancelOrder;

}
