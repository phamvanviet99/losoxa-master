package com.phamvanviet.losoxa.mapper;


import com.phamvanviet.losoxa.entity.*;
import com.phamvanviet.losoxa.model.dto.ReportDTO;
import com.phamvanviet.losoxa.model.response.*;
import com.phamvanviet.losoxa.repository.LineItemRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Converter {

    @Autowired
    private LineItemRepository lineItemRepository;
    @Autowired
    private ModelMapper modelMapper;

    public ProductResponse productToResponse(Product product) {
        ProductResponse productResponse = new ProductResponse();
        productResponse.setId(product.getId());
        productResponse.setName(product.getName());
        productResponse.setUnitPrice(product.getUnitPrice());
        productResponse.setQuantity(product.getQuantity());
        productResponse.setProductImage(product.getProductImage());
        productResponse.setDescription(product.getDescription());
        productResponse.setViews(product.getViews());
        productResponse.setQuantitySold(product.getQuantitySold());
        productResponse.setCategory(product.getCategory());
        return productResponse;
    }

    public UserResponse userToResponse(User userEntity) {
        UserResponse userResponse = new UserResponse();
        userResponse.setId(userEntity.getId());
        userResponse.setUserName(userEntity.getUserName());
        userResponse.setEmail(userEntity.getEmail());
        userResponse.setFullName(userEntity.getFullName());
        userResponse.setPhone(userEntity.getPhone());
        userResponse.setAddress(userEntity.getAddress());
        userResponse.setStatus(userEntity.getStatus());
        userResponse.setPoint(userEntity.getPoint());
        userResponse.setCreatedAt(userEntity.getCreatedAt());
        userResponse.setCreatedBy(userEntity.getCreatedBy());
        userResponse.setModifiedAt(userEntity.getModifiedAt());
        userResponse.setModifiedBy(userEntity.getModifiedBy());

        Role roleEntity = userEntity.getRole();
        RoleResponse roleResponse = new RoleResponse();
        roleResponse.setId(roleEntity.getId());
        roleResponse.setName(roleEntity.getName());
        roleResponse.setDescription(roleEntity.getDescription());
        userResponse.setRole(roleResponse);
        return userResponse;
    }

    public RoleResponse roleToResponse(Role roleEntity) {
        RoleResponse roleResponse = new RoleResponse();
        roleResponse.setId(roleEntity.getId());
        roleResponse.setName(roleEntity.getName());
        roleResponse.setDescription(roleEntity.getDescription());
        List<PermissionResponse> permissionResponses = new ArrayList<>();
        List<Permission> permissionEntities = roleEntity.getPermission();
        for (Permission permissionEntity : permissionEntities) {
            PermissionResponse permissionResponse = new PermissionResponse();
            permissionResponse.setId(permissionEntity.getId());
            permissionResponse.setName(permissionEntity.getName());
            permissionResponse.setDescription(permissionEntity.getDescription());
            permissionResponses.add(permissionResponse);
        }
        roleResponse.setPermission(permissionResponses);
        return roleResponse;
    }

    public OrderResponse orderToResponse(Order order) {
        OrderResponse orderResponse = new OrderResponse();
        BeanUtils.copyProperties(order, orderResponse);
        orderResponse.setUsername(order.getUser().getUserName());
        orderResponse.setOrderDate(order.getCreatedAt());
        List<LineItem> lineItems = lineItemRepository.findLineItemByOrderId(order.getId());
        orderResponse.setItemList(lineItems);
        return orderResponse;
    }

    public FavouriteResponse favouriteToResponse(Favourite favourite){
        FavouriteResponse favouriteResponse = new FavouriteResponse();
        favouriteResponse.setId(favourite.getId());
        favouriteResponse.setProduct(productToResponse(favourite.getProduct()));
        favouriteResponse.setUser(userToResponse(favourite.getUser()));
        return favouriteResponse;
    }

    public DistrictResponse districtToResponse(District district){
        DistrictResponse districtResponse = new DistrictResponse();
        districtResponse.setId(district.getId());
        districtResponse.setName(district.getName());
        districtResponse.setPrefix(district.getPrefix());
        districtResponse.setProvince(modelMapper.provinceToResponse(district.getProvince()));
        return districtResponse;
    }

    public CommuneResponse communeToResponse(Commune commune){
        CommuneResponse communeResponse = new CommuneResponse();
        communeResponse.setId(commune.getId());
        communeResponse.setName(commune.getName());
        communeResponse.setPrefix(commune.getPrefix());
        communeResponse.setDistrict(districtToResponse(commune.getDistrict()));
        communeResponse.setProvince(modelMapper.provinceToResponse(commune.getProvince()));
        return communeResponse;
    }

    public ReportDTO reportToDTO(Report report){
        ReportDTO reportDTO = new ReportDTO();
        reportDTO.setId(report.getId());
        reportDTO.setCreatedDate(report.getCreatedAt());
        reportDTO.setCreatedBy(report.getCreatedBy());
        reportDTO.setUrl(report.getUrl());
        reportDTO.setNameFile(report.getNameFile());
        reportDTO.setPath(report.getPath());
        return reportDTO;
    }

    public RateResponse rateToResponse(Rate rate) {
        RateResponse rateResponse = new RateResponse();
        BeanUtils.copyProperties(rate, rateResponse);
        rateResponse.setUserName(rate.getUser().getUserName());
        rateResponse.setContent(rate.getContent());
        rateResponse.setStar(rate.getStar());
        return rateResponse;
    }
}
