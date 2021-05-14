package com.phamvanviet.losoxa.api.admin;

import com.phamvanviet.losoxa.model.request.product.ProductRequest;
import com.phamvanviet.losoxa.model.response.ProductResponse;
import com.phamvanviet.losoxa.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/admin/api/product")
public class ProductAPI {
    @Autowired
    private ProductService productService;

    @PostMapping
    public ProductResponse create(@Valid  @RequestBody ProductRequest productRequest){
        return productService.apiCreateProduct(productRequest);
    }
//
//    @PutMapping("/{id}")
//    public NewsResponse update(@RequestBody NewsRequest newsRequest, @PathVariable("id") Long id){
//        return newsService.apiUpdateNews(newsRequest, id);
//    }
}
