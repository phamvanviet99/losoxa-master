package com.phamvanviet.losoxa.service;

import com.phamvanviet.losoxa.entity.Category;
import com.phamvanviet.losoxa.entity.Product;
import com.phamvanviet.losoxa.model.request.product.ProductRequest;
import com.phamvanviet.losoxa.model.response.ProductResponse;

import java.util.List;
import java.util.Map;

public interface ProductService {
    List<ProductResponse> getListProduct(int offset, int limit);
    List<ProductResponse> getListProductPopular(int offset, int limit, String keyword);
    List<ProductResponse> getProductAndSort(int limit, int offset);
    List<ProductResponse> getListProductByCategoryId(Long id, int offset, int limit);
    List<ProductResponse> getListProductPopularByCategoryId(Long id, int offset, int limit);
    int getCount();
    int getCountByCategoryId(Long id);
    int getCountFilterPrice(long min, long max);
    Product getProductById(Long id);
    void create(ProductRequest productRequest);
    boolean nameValid(String name);
    void update(Long id, ProductRequest productRequest);
    boolean deleteProduct(Product product);
    List<ProductResponse> getListProductFilterPrice(int offset, int limit, long min, long max);
    List<Product> findProductRelate(Long id, Long categoryId);
    void viewIncrement(Long id);
    List<ProductResponse> getProductFavourite(Long userId);
    List<ProductResponse> getProductPopular();
    Map<Integer, Integer> statistical();
    int countQuantitySold();


    /*api*/
    ProductResponse apiCreateProduct(ProductRequest productRequest);
}
