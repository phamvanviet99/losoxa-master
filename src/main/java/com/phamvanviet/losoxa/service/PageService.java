package com.phamvanviet.losoxa.service;

import com.phamvanviet.losoxa.entity.Category;
import com.phamvanviet.losoxa.entity.Favourite;
import com.phamvanviet.losoxa.entity.Product;
import com.phamvanviet.losoxa.entity.User;
import com.phamvanviet.losoxa.model.response.*;

import java.util.List;

public interface PageService{
    PageResponse pageAdminProduct(int offset, int limit, int page, List<ProductResponse> productResponses);

    PageResponse pageAdminUser(int offset, int limit, int page, List<UserResponse> users);

    PageResponse pageAdminCategory(int offset, int limit, int page, List<CategoryResponse> categories);

    PageResponse pageAdminOrder(int offset, int limit, int page, List<OrderResponse> orderResponses);

    PageResponse pageOrderByUser(int offset, int limit, int page, List<OrderResponse> orderResponses);

    PageResponse pageFavouriteProduct(int offset, int limit, int page, List<FavouriteResponse> favourites);

    PageResponse pageAdminRole(int offset, int limit, int page, List<RoleResponse> roleResponses);

    PageResponse pageAdminPermission(int offset, int limit, int page, List<PermissionResponse> permissionResponses);

    PageResponse pageAdminBlog(int offset, int limit, int page, List<BlogResponse> blogResponses);
}
