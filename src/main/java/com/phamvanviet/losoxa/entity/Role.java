package com.phamvanviet.losoxa.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "roles")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Role extends Base {
    @Column(length = 20, unique = true, nullable = false)
    private String name;

    @Column(length = 100)
    private String description;

    @OneToMany(mappedBy = "role")
    Set<User> users = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "role_permission",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id"))
    private List<Permission> permission = new ArrayList<>();
}
