package com.phamvanviet.losoxa.service.impl;

import com.phamvanviet.losoxa.entity.Category;
import com.phamvanviet.losoxa.entity.Favourite;
import com.phamvanviet.losoxa.entity.User;
import com.phamvanviet.losoxa.model.response.*;
import com.phamvanviet.losoxa.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PageServiceImpl implements PageService {

    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private OrderService orderService;

    @Override
    public PageResponse pageAdminProduct(int offset, int limit, int page, List<ProductResponse> productResponses) {
        PageResponse pageResponse = new PageResponse();
        pageResponse.setPage(page);
        pageResponse.setLimit(limit);
        pageResponse.setListProduct(productResponses);
        pageResponse.setTotalItem(productService.getCount());
        pageResponse.setTotalPage((int) Math.ceil((double) pageResponse.getTotalItem()/pageResponse.getLimit()));
        return pageResponse;
    }

    @Override
    public PageResponse pageAdminUser(int offset, int limit, int page, List<UserResponse> users) {
        PageResponse pageResponse = new PageResponse();
        pageResponse.setPage(page);
        pageResponse.setLimit(limit);
        pageResponse.setListUser(users);
        pageResponse.setTotalItem(userService.getCount());
        pageResponse.setTotalPage((int) Math.ceil((double) pageResponse.getTotalItem()/pageResponse.getLimit()));
        return pageResponse;
    }

    @Override
    public PageResponse pageAdminCategory(int offset, int limit, int page, List<CategoryResponse> categories) {
        PageResponse pageResponse = new PageResponse();
        pageResponse.setPage(page);
        pageResponse.setLimit(limit);
        pageResponse.setListCategory(categories);
        pageResponse.setTotalItem(categoryService.getCount());
        pageResponse.setTotalPage((int) Math.ceil((double) pageResponse.getTotalItem()/pageResponse.getLimit()));
        return pageResponse;
    }

    @Override
    public PageResponse pageAdminOrder(int offset, int limit, int page, List<OrderResponse> orderResponses) {
        PageResponse pageResponse = new PageResponse();
        pageResponse.setPage(page);
        pageResponse.setLimit(limit);
        pageResponse.setListOrder(orderResponses);
        pageResponse.setTotalItem(orderService.getCount());
        pageResponse.setTotalPage((int) Math.ceil((double) pageResponse.getTotalItem()/pageResponse.getLimit()));
        return pageResponse;
    }

    @Override
    public PageResponse pageOrderByUser(int offset, int limit, int page, List<OrderResponse> orderResponses) {
        PageResponse pageResponse = new PageResponse();
        pageResponse.setPage(page);
        pageResponse.setLimit(limit);
        pageResponse.setListOrder(orderResponses);
        pageResponse.setTotalItem(orderResponses.size());
        pageResponse.setTotalPage((int) Math.ceil((double) pageResponse.getTotalItem()/pageResponse.getLimit()));
        return pageResponse;
    }

    @Override
    public PageResponse pageFavouriteProduct(int offset, int limit, int page, List<FavouriteResponse> favourites) {
        PageResponse pageResponse = new PageResponse();
        pageResponse.setPage(page);
        pageResponse.setLimit(limit);
        pageResponse.setListFavourite(favourites);
        pageResponse.setTotalItem(favourites.size());
        pageResponse.setTotalPage((int) Math.ceil((double) pageResponse.getTotalItem()/pageResponse.getLimit()));
        return pageResponse;
    }

    @Override
    public PageResponse pageAdminRole(int offset, int limit, int page, List<RoleResponse> roleResponses) {
        PageResponse pageResponse = new PageResponse();
        pageResponse.setPage(page);
        pageResponse.setLimit(limit);
        pageResponse.setListRole(roleResponses);
        pageResponse.setTotalItem(roleResponses.size());
        pageResponse.setTotalPage((int) Math.ceil((double) pageResponse.getTotalItem()/pageResponse.getLimit()));
        return pageResponse;
    }

    @Override
    public PageResponse pageAdminPermission(int offset, int limit, int page, List<PermissionResponse> permissionResponses) {
        PageResponse pageResponse = new PageResponse();
        pageResponse.setPage(page);
        pageResponse.setLimit(limit);
        pageResponse.setListPermission(permissionResponses);
        pageResponse.setTotalItem(permissionResponses.size());
        pageResponse.setTotalPage((int) Math.ceil((double) pageResponse.getTotalItem()/pageResponse.getLimit()));
        return pageResponse;
    }

    @Override
    public PageResponse pageAdminBlog(int offset, int limit, int page, List<BlogResponse> blogResponses) {
        PageResponse pageResponse = new PageResponse();
        pageResponse.setPage(page);
        pageResponse.setLimit(limit);
        pageResponse.setListBlog(blogResponses);
        pageResponse.setTotalItem(blogResponses.size());
        pageResponse.setTotalPage((int) Math.ceil((double) pageResponse.getTotalItem()/pageResponse.getLimit()));
        return pageResponse;
    }
}
