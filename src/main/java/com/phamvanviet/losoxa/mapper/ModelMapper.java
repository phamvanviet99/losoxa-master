package com.phamvanviet.losoxa.mapper;

import com.phamvanviet.losoxa.entity.Blog;
import com.phamvanviet.losoxa.entity.Category;
import com.phamvanviet.losoxa.entity.Permission;
import com.phamvanviet.losoxa.entity.Province;
import com.phamvanviet.losoxa.model.response.BlogResponse;
import com.phamvanviet.losoxa.model.response.CategoryResponse;
import com.phamvanviet.losoxa.model.response.PermissionResponse;
import com.phamvanviet.losoxa.model.response.ProvinceResponse;
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
}
