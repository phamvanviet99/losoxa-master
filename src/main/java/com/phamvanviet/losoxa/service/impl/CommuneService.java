package com.phamvanviet.losoxa.service.impl;

import com.phamvanviet.losoxa.entity.Commune;
import com.phamvanviet.losoxa.mapper.Converter;
import com.phamvanviet.losoxa.model.response.CommuneResponse;
import com.phamvanviet.losoxa.repository.CommuneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommuneService {
    @Autowired
    private CommuneRepository communeRepository;
    @Autowired
    private Converter converter;

    public List<CommuneResponse> findByDistrictId(Long id) {
        List<Commune> communes = communeRepository.findAllByDistrictId(id);
        List<CommuneResponse> communeResponses = new ArrayList<>();
        communes.forEach(commune -> communeResponses.add(converter.communeToResponse(commune)));
        return communeResponses;
    }

}
