package com.phamvanviet.losoxa.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "blog")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Blog extends Base{
    @Column(name = "title")
    private String title;

    @Column(name = "thumbnail")
    private String thumbnail;

    @Column(name = "short_description")
    @Type(type = "text")
    private String shortDescription;

    @Column(name = "content")
    @Type(type = "text")
    private String content;

    @Column(name = "views")
    private Integer views;
}
