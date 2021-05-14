package com.phamvanviet.losoxa.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Table(name = "commune")
@Entity
@Getter
@Setter
public class Commune {
    @Id
    private Long id;

    @Column
    private String name;

    @Column(length = 20)
    private String prefix;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "district")
    private District district;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "province")
    private Province province;

    @ManyToMany
    @JoinTable(
            name = "user_commune",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "commune_id"))
    private List<User> users;
}
