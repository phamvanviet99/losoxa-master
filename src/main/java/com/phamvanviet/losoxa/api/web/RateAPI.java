package com.phamvanviet.losoxa.api.web;

import com.phamvanviet.losoxa.model.request.blog.BlogRequest;
import com.phamvanviet.losoxa.model.request.rate.RateRequest;
import com.phamvanviet.losoxa.model.response.BlogResponse;
import com.phamvanviet.losoxa.model.response.RateResponse;
import com.phamvanviet.losoxa.service.BlogService;
import com.phamvanviet.losoxa.service.RateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/rate")
public class RateAPI {
    @Autowired
    private RateService rateService;

    @PostMapping
    public RateResponse post(@RequestBody RateRequest rateRequest){
        return rateService.apiCreateRate(rateRequest);
    }

    @PostMapping("/list")
    public List<RateResponse> getList(@RequestBody RateRequest rateRequest){
        return rateService.getListByProduct(rateRequest);
    }
}
