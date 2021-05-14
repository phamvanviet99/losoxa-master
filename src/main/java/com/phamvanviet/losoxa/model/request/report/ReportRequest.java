package com.phamvanviet.losoxa.model.request.report;

import lombok.Data;

import java.util.Date;

@Data
public class ReportRequest {
    private Long id;

    private Date startDate;
    private Date endDate;
    private Integer platingYear;

    private String userName;
    private String fullName;
    private String phone;
    private String email;

}
