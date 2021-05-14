package com.phamvanviet.losoxa.model.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommuneResponse {
    private Long id;

    private String name;

    private String prefix;

    private DistrictResponse district;

    private ProvinceResponse province;
}
