package com.phamvanviet.losoxa.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "category")
public class Category extends Base {
    @Column(unique = true, nullable = false, length = 50)
    private String name;

    @Column
    @Type(type = "text")
    private String categoryImage;

    @OneToMany(mappedBy = "category")
    List<Product> products = new ArrayList<>();
}
