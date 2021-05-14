package com.phamvanviet.losoxa.service;

import com.phamvanviet.losoxa.entity.Province;
import com.phamvanviet.losoxa.mapper.ModelMapper;
import com.phamvanviet.losoxa.model.response.ProvinceResponse;
import com.phamvanviet.losoxa.repository.ProvinceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProvinceService {
    @Autowired
    private ProvinceRepository provinceRepository;
    @Autowired
    private ModelMapper modelMapper;

    public List<ProvinceResponse> findAllProvince() {
        List<ProvinceResponse> provinceResponses = new ArrayList<>();
        List<Province> provinces = provinceRepository.findAll();
        provinces.forEach(province -> provinceResponses.add(modelMapper.provinceToResponse(province)));
        return provinceResponses;
    }
}
