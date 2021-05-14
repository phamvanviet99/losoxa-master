package com.phamvanviet.losoxa.api.admin.shippingAddress;

import com.phamvanviet.losoxa.model.response.ProvinceResponse;
import com.phamvanviet.losoxa.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/admin/province")
public class ProvinceAPI {
    @Autowired
    private ProvinceService provinceService;

    @GetMapping
    public List<ProvinceResponse> getAll() {
        return provinceService.findAllProvince();
    }


}
