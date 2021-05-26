package com.phamvanviet.losoxa.service.impl;

import com.phamvanviet.losoxa.entity.Blog;
import com.phamvanviet.losoxa.entity.LineItem;
import com.phamvanviet.losoxa.entity.Order;
import com.phamvanviet.losoxa.entity.Rate;
import com.phamvanviet.losoxa.mapper.Converter;
import com.phamvanviet.losoxa.mapper.ModelMapper;
import com.phamvanviet.losoxa.model.request.rate.RateRequest;
import com.phamvanviet.losoxa.model.response.RateResponse;
import com.phamvanviet.losoxa.repository.LineItemRepository;
import com.phamvanviet.losoxa.repository.OrderRepository;
import com.phamvanviet.losoxa.repository.RateRepository;
import com.phamvanviet.losoxa.repository.UserRepository;
import com.phamvanviet.losoxa.service.ProductService;
import com.phamvanviet.losoxa.service.RateService;
import com.phamvanviet.losoxa.service.UserService;
import com.phamvanviet.losoxa.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class RateServiceImpl implements RateService {
    @Autowired
    private ProductService productService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RateRepository rateRepository;
    @Autowired
    private Converter converter;
    @Autowired
    private LineItemRepository lineItemRepository;


    @Override
    public RateResponse apiCreateRate(RateRequest rateRequest) {
        List<LineItem> lineItems = lineItemRepository.findByUserIdAndProductId(rateRequest.getUserId(), rateRequest.getProductId());
        if (lineItems.size()>0){
            Rate rate = new Rate();
            rate.setStar(rateRequest.getStar());
            rate.setContent(rateRequest.getContent());
            rate.setProduct(productService.getProductById(rateRequest.getProductId()));
            rate.setUser(userRepository.findUserById(rateRequest.getUserId()));
            rate.setCreatedAt(new Date());
            rate.setCreatedBy(SecurityUtils.getPrinciple().getFullName());
            rateRepository.save(rate);
            return converter.rateToResponse(rate);
        }
        return null;

    }

    @Override
    public List<RateResponse> getListByProduct(RateRequest rateRequest) {
        List<Rate> list = rateRepository.listRateByProduct(rateRequest.getProductId());
        List<RateResponse> rateResponses = new ArrayList<>();
        list.forEach(rate -> rateResponses.add(converter.rateToResponse(rate)));
        return rateResponses;
    }
}
