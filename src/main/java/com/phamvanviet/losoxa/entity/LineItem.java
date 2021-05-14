package com.phamvanviet.losoxa.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "line_items")
public class LineItem extends Base {
    @ManyToOne(fetch = FetchType.LAZY)
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

    @Column
    private Long unitPrice;

    @Column
    private int quantity;
}
