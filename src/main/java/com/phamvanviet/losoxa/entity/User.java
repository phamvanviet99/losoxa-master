package com.phamvanviet.losoxa.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User extends Base {
    @Column(nullable = false, unique = true, length = 50)
    private String userName;

    @Column(nullable = false, length = 150)
    private String password;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(length = 50)
    private String fullName;

    @Column(length = 10)
    private String phone;

    @Column(length = 100)
    private String address;

    @Column(nullable = false)
    private Boolean status;

    @Column
    private Integer point;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @ManyToMany(mappedBy = "users")
    private List<Commune> communes;
}
