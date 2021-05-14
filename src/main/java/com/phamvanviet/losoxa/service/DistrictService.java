package com.phamvanviet.losoxa.service;

import com.phamvanviet.losoxa.entity.District;
import com.phamvanviet.losoxa.mapper.Converter;
import com.phamvanviet.losoxa.model.response.DistrictResponse;
import com.phamvanviet.losoxa.repository.DistrictRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DistrictService {
    @Autowired
    private DistrictRepository districtRepository;
    @Autowired
    private Converter converter;

    public List<DistrictResponse> findByProvineId(Long id) {
        List<District> districts = districtRepository.findAllByProvinceId(id);
        List<DistrictResponse> districtResponses = new ArrayList<>();
        districts.forEach(district -> districtResponses.add(converter.districtToResponse(district)));
        return districtResponses;
    }
}
