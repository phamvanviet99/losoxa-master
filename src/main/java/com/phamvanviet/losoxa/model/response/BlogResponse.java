package com.phamvanviet.losoxa.model.response;

import lombok.Data;

@Data
public class BlogResponse extends BaseResponse{
    private String title;
    private String thumbnail;
    private String shortDescription;
    private String content;
    private Integer views;
}
