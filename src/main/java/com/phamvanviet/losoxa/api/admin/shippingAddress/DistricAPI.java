package com.phamvanviet.losoxa.api.admin.shippingAddress;

import com.phamvanviet.losoxa.model.response.DistrictResponse;
import com.phamvanviet.losoxa.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/admin/district")
public class DistricAPI {
    @Autowired
    private DistrictService districtService;

    @GetMapping
    public List<DistrictResponse> findAllByProvineId(@RequestParam(name = "province", required = false) Long id) {
        return districtService.findByProvineId(id);
    }
}
