package com.phamvanviet.losoxa.api.admin.shippingAddress;

import com.phamvanviet.losoxa.model.response.CommuneResponse;
import com.phamvanviet.losoxa.service.impl.CommuneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/admin/commune")
public class CommuneAPI {
    @Autowired
    private CommuneService communeService;

    @GetMapping
    public List<CommuneResponse> findByDistrictId(@RequestParam(name = "district", required = false) Long id) {
        return communeService.findByDistrictId(id);
    }
}
