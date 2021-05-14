package com.phamvanviet.losoxa.service;

import com.phamvanviet.losoxa.entity.Category;
import com.phamvanviet.losoxa.model.request.category.CategoryRequest;
import com.phamvanviet.losoxa.model.response.CategoryResponse;

import java.util.List;
import java.util.Map;

public interface CategoryService {
    List<Category> getListProductCategory();

    List<CategoryResponse> getCategoryAndSort(int offset, int limit);

    void create(CategoryRequest categoryRequest);

    void update(Long id, CategoryRequest categoryRequest);

    boolean nameValid(String name);

    void delete(Long id);

    int getCount();

    List<Category> getCategoryPopular();
}
