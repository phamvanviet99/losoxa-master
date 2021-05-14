package com.phamvanviet.losoxa.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "products")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product extends Base {

    @Column(length = 100, nullable = false, unique = true)
    private String name;

    @Column
    private Long unitPrice;

    @Column
    private Integer quantity;

    @Column
    @Type(type = "text")
    private String productImage;

    @Column
    @Type(type = "text")
    private String description;

    @Column
    private Integer views;

    @Column
    private Integer quantitySold;

    @ManyToOne
    private Category category;

//    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "products")
//    private Set<User> users = new HashSet<>();
}
