package com.phamvanviet.losoxa.mapper;

import com.phamvanviet.losoxa.entity.*;
import com.phamvanviet.losoxa.model.response.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface ModelMapper {

    @Mapping(target = "parentId", source = "parent.id")
    PermissionResponse permissionToResponse(Permission permissionEntity);

    CategoryResponse categoryToResponse(Category categoryEntity);

    BlogResponse blogToResponse(Blog blog);

    ProvinceResponse provinceToResponse(Province province);

    RateResponse rateToResponse(Rate rate);
}
