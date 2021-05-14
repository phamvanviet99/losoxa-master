package com.phamvanviet.losoxa.service.impl;

import com.phamvanviet.losoxa.entity.Category;
import com.phamvanviet.losoxa.entity.Product;
import com.phamvanviet.losoxa.mapper.ModelMapper;
import com.phamvanviet.losoxa.model.request.category.CategoryRequest;
import com.phamvanviet.losoxa.model.request.page.PageRequest;
import com.phamvanviet.losoxa.model.response.CategoryResponse;
import com.phamvanviet.losoxa.repository.CategoryRepository;
import com.phamvanviet.losoxa.repository.ProductRepository;
import com.phamvanviet.losoxa.service.CategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Stream;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public List<Category> getListProductCategory() {
        List<Category> listCategory = categoryRepository.findAllByArrangeName();
        return listCategory;
    }

    @Override
    public List<CategoryResponse> getCategoryAndSort(int offset, int limit) {
        PageRequest pageable = new PageRequest(offset, limit, Sort.by("id").descending());
        List<Category> categories = categoryRepository.findCategoryList(pageable);
        List<CategoryResponse> categoryResponses = new ArrayList<>();
        for(Category category:categories){
            categoryResponses.add(modelMapper.categoryToResponse(category));
        }
        return categoryResponses;
    }

    @Override
    public void create(CategoryRequest categoryRequest) {
        Category category = new Category();
        category.setName(categoryRequest.getName());
        category.setCategoryImage(categoryRequest.getCategoryImage());
        categoryRepository.save(category);
    }

    @Override
    public void update(Long id, CategoryRequest categoryRequest) {
        Category category = categoryRepository.findCategoryById(id);
        category.setName(categoryRequest.getName());
        category.setCategoryImage(categoryRequest.getCategoryImage());
        categoryRepository.save(category);
    }

    @Override
    public boolean nameValid(String name) {
        return (categoryRepository.findCategoryByName(name) == null);
    }

    @Override
    public void delete(Long id) {
        List<Product> products = productRepository.findByCategory_Id(id);
        if (products.isEmpty()){
            categoryRepository.deleteById(id);

        }
        else {

//            ModelMap modelMap = new ModelMap();
//            modelMap.addAttribute("message", "Thể loại này đang tồn tại sản phẩm!");
//            return "redirect:admin/message/message";

        }
    }

    @Override
    public int getCount() {
        return (int)categoryRepository.count();
    }

    @Override
    public List<Category> getCategoryPopular() {
        Map<Category, Integer> countOrder = new HashMap<>();
        List<Category> categories = categoryRepository.findAll();
        for (Category category:categories){
            List<Product> products = productRepository.findByCategory_Id(category.getId());
            int count =0;
            for (Product product: products){
                count+=product.getQuantitySold();
            }
            countOrder.put(category, count);
        }
        List<Category> sortedList = new ArrayList<>();

        countOrder.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEachOrdered(x -> sortedList.add(x.getKey()));
        return sortedList;
    }
}
