package com.phamvanviet.losoxa.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order extends Base{
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @Column
    private String note;

    @Column
    private String shippingAddress;

    @Column
    private String status;

    @Column
    private Long totalPrice;

    @Column
    private Integer point;

    @Column
    private Integer paymentType;

}
