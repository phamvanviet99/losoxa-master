package com.phamvanviet.losoxa.service.impl;

import com.phamvanviet.losoxa.entity.Category;
import com.phamvanviet.losoxa.entity.Product;
import com.phamvanviet.losoxa.mapper.Converter;
import com.phamvanviet.losoxa.model.request.page.PageRequest;
import com.phamvanviet.losoxa.model.request.product.ProductRequest;
import com.phamvanviet.losoxa.model.response.ProductResponse;
import com.phamvanviet.losoxa.repository.CategoryRepository;
import com.phamvanviet.losoxa.repository.FavouriteRepository;
import com.phamvanviet.losoxa.repository.ProductRepository;
import com.phamvanviet.losoxa.service.ProductService;
import com.phamvanviet.losoxa.util.SecurityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    private CategoryRepository categoryRepository;

    private FavouriteRepository favouriteRepository;

    private Converter converter;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository, FavouriteRepository favouriteRepository, Converter converter) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.favouriteRepository = favouriteRepository;
        this.converter = converter;
    }

    @Override
    public List<ProductResponse> getListProduct(int offset, int limit) {
        PageRequest pageRequest = new PageRequest(offset, limit, Sort.by("id").descending());
        List<ProductResponse> productResponses = new ArrayList<>();
        List<Product> products = productRepository.findAllAndSort(pageRequest);
        products.forEach(product -> productResponses.add(converter.productToResponse(product)));
        return productResponses;
    }

    @Override
    public List<ProductResponse> getListProductPopular(int offset, int limit, String keyword) {
        PageRequest pageRequest = new PageRequest(offset, limit, Sort.by("id").descending());
        List<ProductResponse> productResponses = new ArrayList<>();
        if (keyword != null) {
            List<Product> products = productRepository.search(keyword, pageRequest);
            products.forEach(product -> productResponses.add(converter.productToResponse(product)));
            return productResponses;
        }
        List<Product> products = productRepository.findAllPopular(pageRequest);
        products.forEach(product -> productResponses.add(converter.productToResponse(product)));
        return productResponses;
    }

    @Override
    public List<ProductResponse> getProductAndSort(int offset, int limit) {
        PageRequest pageable = new PageRequest(offset, limit, Sort.by("id").descending());
        List<Product> products = productRepository.findAllAndSort(pageable);
        List<ProductResponse> productResponses = new ArrayList<>();
        for (Product product : products) {
            productResponses.add(converter.productToResponse(product));
        }
        return productResponses;
    }

    @Override
    public List<ProductResponse> getListProductByCategoryId(Long id, int offset, int limit) {
        PageRequest pageRequest = new PageRequest(offset, limit, Sort.by("id").descending());
        List<Product> products = productRepository.findAllByCategoryId(id, pageRequest);
        List<ProductResponse> productResponses = new ArrayList<>();
        for (Product product : products) {
            productResponses.add(converter.productToResponse(product));
        }
        return productResponses;
    }

    @Override
    public List<ProductResponse> getListProductPopularByCategoryId(Long id, int offset, int limit) {
        PageRequest pageRequest = new PageRequest(offset, limit, Sort.by("id").descending());
        List<Product> products = productRepository.findAllPopularByCategoryId(id, pageRequest);
        List<ProductResponse> productResponses = new ArrayList<>();
        for (Product product : products) {
            productResponses.add(converter.productToResponse(product));
        }
        return productResponses;
    }

    @Override
    public int getCount() {
        return (int) productRepository.count();
    }

    @Override
    public int getCountByCategoryId(Long id) {
        return (int) productRepository.countProductByCategory(id);
    }

    @Override
    public int getCountFilterPrice(long min, long max) {
        return productRepository.countProductByFilterPrice(min, max);
    }


    @Override
    public Product getProductById(Long id) {
        Product product = productRepository.findOneById(id);
        return product;
    }

    @Override
    public void create(ProductRequest productRequest) {
        Product product = new Product();
        BeanUtils.copyProperties(productRequest, product);
        product.setQuantity(Integer.parseInt(productRequest.getQuantity()));
        product.setUnitPrice(Long.parseLong(productRequest.getUnitPrice()));
        product.setProductImage(productRequest.getProductImage());
        product.setViews(0);
        product.setQuantitySold(0);
        product.setCreatedBy(SecurityUtils.getPrinciple().getUsername());
        product.setCreatedAt(new Date());
        product.setDescription(productRequest.getDescription());
        Long categoryId = productRequest.getCategoryId();
        if (categoryId != null) {
            Category category = categoryRepository.findCategoryById(categoryId);
            product.setCategory(category);
        }
        productRepository.save(product);
    }

    @Override
    public boolean nameValid(String name) {
        return (productRepository.findProductByName(name) == null);
    }

    @Override
    public void update(Long id, ProductRequest productRequest) {
        Product product = productRepository.getOne(id);
        BeanUtils.copyProperties(productRequest, product);
        product.setQuantity(Integer.parseInt(productRequest.getQuantity()));
        product.setUnitPrice(Long.parseLong(productRequest.getUnitPrice()));
        product.setProductImage(productRequest.getProductImage());
        product.setModifiedBy(SecurityUtils.getPrinciple().getUsername());
        product.setModifiedAt(new Date());
        product.setDescription(productRequest.getDescription());
        Long categoryId = productRequest.getCategoryId();
        if (categoryId != null) {
            Category brand = categoryRepository.findCategoryById(categoryId);
            product.setCategory(brand);
        }
        productRepository.save(product);
    }

    @Override
    public boolean deleteProduct(Product product) {
//        if (lineItemService.findByProduct(product).isEmpty()) {
//            productRepository.delete(product);
//            return true;
//        }
        productRepository.delete(product);
        return true;
    }

    @Override
    public List<ProductResponse> getListProductFilterPrice(int offset, int limit, long min, long max) {
        PageRequest pageRequest = new PageRequest(offset, limit, Sort.by("id").descending());
        List<Product> products = productRepository.findAllAndSortFilterPrice(pageRequest, min, max);
        List<ProductResponse> productResponses = new ArrayList<>();
        for (Product product : products) {
            productResponses.add(converter.productToResponse(product));
        }
        return productResponses;
    }

    @Override
    public List<Product> findProductRelate(Long id, Long categoryId) {
        List<Product> products = productRepository.findProductRelate(id, categoryId);
        return products;
    }

    @Override
    public void viewIncrement(Long id) {
        Product product = productRepository.findOneById(id);
        product.setViews(product.getViews() + 1);
        productRepository.save(product);
    }

    @Override
    public List<ProductResponse> getProductFavourite(Long userId) {
        List<Product> products = favouriteRepository.getProductFavourite(userId);
        List<ProductResponse> productResponses = new ArrayList<>();
        products.forEach(product -> productResponses.add(converter.productToResponse(product)));
        return productResponses;
    }

    @Override
    public List<ProductResponse> getProductPopular() {
        List<Product> products = productRepository.findAllAndSortByQuantitySold();
        List<ProductResponse> productResponses = new ArrayList<>();
        products.forEach(product -> productResponses.add(converter.productToResponse(product)));
        return productResponses;
    }

    @Override
    public Map<Integer, Integer> statistical() {
        Map<Integer, Integer> statistical = new HashMap<>();
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM");
        int month = Integer.parseInt(simpleDateFormat.format(date).toUpperCase());
        simpleDateFormat = new SimpleDateFormat("YYYY");
        int year = Integer.parseInt(simpleDateFormat.format(date).toUpperCase());
        int index = 0;
        for (int i = month-1; i>=0; i--) {
            int count = productRepository.countProductByMonth(month - i, year);
            index++;
            statistical.put(index, count);
        }
        return statistical;
    }

    @Override
    public int countQuantitySold() {
        return productRepository.countQuantitySold();
    }


    /*api*/

    @Override
    public ProductResponse apiCreateProduct(ProductRequest productRequest) {
        Product product = Product.builder()
                .name(productRequest.getName())
                .unitPrice(Long.parseLong(productRequest.getUnitPrice()))
                .quantity(Integer.parseInt(productRequest.getQuantity()))
                .productImage(productRequest.getProductImage())
                .description(productRequest.getDescription())
                .views(0)
                .quantitySold(0)
                .category(categoryRepository.findCategoryById(productRequest.getCategoryId()))
                .build();
        product.setCreatedAt(new Date());
        product.setCreatedBy(SecurityUtils.getPrinciple().getUsername());
        productRepository.save(product);
        return converter.productToResponse(product);
    }

}
