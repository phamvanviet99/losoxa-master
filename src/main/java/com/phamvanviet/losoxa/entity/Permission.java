package com.phamvanviet.losoxa.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "permissions")
public class    Permission extends Base{

    @Column(length = 30, unique = true, nullable = false)
    private String name;

    @Column(length = 100)
    private String description;

    @ManyToMany(mappedBy = "permission")
    private List<Role> role = new ArrayList<>();

    /*bonus*/
    @ManyToOne
    @JoinColumn(name = "parent_id",referencedColumnName = "id")
    @JsonBackReference
    private Permission parent;

    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Permission> children = new ArrayList<>();
}
