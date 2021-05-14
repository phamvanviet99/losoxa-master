package com.phamvanviet.losoxa.service;

import com.phamvanviet.losoxa.model.request.blog.BlogRequest;
import com.phamvanviet.losoxa.model.response.BlogResponse;
import com.phamvanviet.losoxa.model.response.ProductResponse;

import java.util.List;

public interface BlogService {
    List<BlogResponse> getPageBlog(int offset, int limit);

    BlogResponse findById(Long id);

    void deleteBlog(Long id);

    List<BlogResponse> getBlogAndSort(int limit, int offset);

    void viewIncrement(Long id);

    List<BlogResponse> findBlogTopView(Long id);

    /*api*/

    BlogResponse apiCreateBlog(BlogRequest blogRequest);

    BlogResponse apiUpdateBlog(BlogRequest blogRequest, Long id);

}
