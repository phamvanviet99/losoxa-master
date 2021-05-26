package com.phamvanviet.losoxa.service;

import com.phamvanviet.losoxa.model.request.blog.BlogRequest;
import com.phamvanviet.losoxa.model.request.rate.RateRequest;
import com.phamvanviet.losoxa.model.response.BlogResponse;
import com.phamvanviet.losoxa.model.response.RateResponse;

import java.util.List;

public interface RateService {
    RateResponse apiCreateRate(RateRequest rateRequest);

    List<RateResponse> getListByProduct(RateRequest rateRequest);
}
