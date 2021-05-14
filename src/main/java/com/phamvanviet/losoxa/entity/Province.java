package com.phamvanviet.losoxa.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table(name = "province")
@Entity
@Getter
@Setter
public class Province {
    @Id
    private Long id;
    @Column
    private String name;
    @Column
    private String code;

    @OneToMany(mappedBy = "province",fetch = FetchType.LAZY)
    List<District> district = new ArrayList<>();

    @OneToMany(mappedBy = "province",fetch = FetchType.LAZY)
    List<Commune> commune = new ArrayList<>();
}
