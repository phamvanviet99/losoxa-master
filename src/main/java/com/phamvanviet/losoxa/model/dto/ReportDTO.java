package com.phamvanviet.losoxa.model.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class ReportDTO {
    private Date createdDate;
    private LocalDate modifiedDate;
    private String createdBy;
    private String modifiedBy;

    private Long id;

    private String reportCode;

    private String url;

    private String nameFile;

    private String path;


    private Date start;
    private Date end;
}
