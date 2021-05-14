package com.phamvanviet.losoxa.api.admin;

import com.phamvanviet.losoxa.model.request.blog.BlogRequest;
import com.phamvanviet.losoxa.model.response.BlogResponse;
import com.phamvanviet.losoxa.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/admin/blog")
public class BlogAPI {
    @Autowired
    private BlogService blogService;

    @PostMapping
    public BlogResponse create(@Valid @RequestBody BlogRequest blogRequest){
        return blogService.apiCreateBlog(blogRequest);
    }

    @PutMapping("/{id}")
    public BlogResponse update(@Valid @RequestBody BlogRequest blogRequest, @PathVariable("id") Long id){
        return blogService.apiUpdateBlog(blogRequest, id);
    }
}
