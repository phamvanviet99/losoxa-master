package com.phamvanviet.losoxa.model.response;

import com.phamvanviet.losoxa.entity.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PageResponse {
    private int page;
    private int limit;
    private int totalItem;
    private int totalPage;
    private List<ProductResponse> listProduct;
    private List<UserResponse> listUser;
    private List<CategoryResponse> listCategory;
    private List<OrderResponse> listOrder;
    private List<FavouriteResponse> listFavourite;
    private List<RoleResponse> listRole;
    private List<PermissionResponse> listPermission;
    private List<BlogResponse> listBlog;

}
