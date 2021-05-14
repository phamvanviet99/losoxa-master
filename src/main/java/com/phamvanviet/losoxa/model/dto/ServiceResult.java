package com.phamvanviet.losoxa.model.dto;

import lombok.Data;

@Data
public class ServiceResult {
    private Object data;
    private String message;
    private Integer totalPage;
    private Integer currentPage;
    private String code;
    private Long count;
    public ServiceResult(){

    }

    public ServiceResult(Object data, Integer totalPage, Integer currentPage) {
        this.data = data;
        this.totalPage = totalPage;
        this.currentPage = currentPage;
    }
    public ServiceResult(Object data, Integer totalPage, Integer currentPage,Long count) {
        this.data = data;
        this.totalPage = totalPage;
        this.currentPage = currentPage;
        this.count = count;
    }

    public ServiceResult(Object data, String message) {
        this.data = data;
        this.message = message;
    }
    public ServiceResult(String message,String code){
        this.code = code;
        this.message = message;
    }

    public ServiceResult(String message) {
        this.message = message;
    }

    public ServiceResult(Object data, String message,String code) {
        this.data = data;
        this.message = message;
        this.code = code;
    }
}
