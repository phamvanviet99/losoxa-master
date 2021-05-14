package com.phamvanviet.losoxa.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "report")
public class Report extends Base{
    private String reportCode;

    @Column(columnDefinition = "varchar(500)")
    private String url;

    private String nameFile;

    private String path;

}
