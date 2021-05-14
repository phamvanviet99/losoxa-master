package com.phamvanviet.losoxa.service.impl;

import com.phamvanviet.losoxa.entity.Blog;
import com.phamvanviet.losoxa.entity.Product;
import com.phamvanviet.losoxa.mapper.ModelMapper;
import com.phamvanviet.losoxa.model.request.blog.BlogRequest;
import com.phamvanviet.losoxa.model.request.page.PageRequest;
import com.phamvanviet.losoxa.model.response.BlogResponse;
import com.phamvanviet.losoxa.model.response.ProductResponse;
import com.phamvanviet.losoxa.repository.BlogRepository;
import com.phamvanviet.losoxa.service.BlogService;
import com.phamvanviet.losoxa.util.SecurityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {
    @Autowired
    private BlogRepository blogRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<BlogResponse> getPageBlog(int offset, int limit) {
        PageRequest pageRequest = new PageRequest(offset, limit, Sort.by("id").descending());
        List<Blog> blogs = blogRepository.getAll(pageRequest);
        List<BlogResponse> blogResponses = new ArrayList<>();
        for (Blog blog : blogs){
            blogResponses.add(modelMapper.blogToResponse(blog));
        }
        return blogResponses;
    }

    @Override
    public BlogResponse findById(Long id) {
        return modelMapper.blogToResponse(blogRepository.findBlogById(id));
    }

    @Override
    public void deleteBlog(Long id) {
        blogRepository.deleteById(id);
    }

    @Override
    public List<BlogResponse> getBlogAndSort(int offset, int limit) {
        PageRequest pageable = new PageRequest(offset, limit, Sort.by("id").descending());
        List<Blog> blogs = blogRepository.findAllAndSort(pageable);
        List<BlogResponse> blogResponses = new ArrayList<>();
        for (Blog blog : blogs){
            blogResponses.add(modelMapper.blogToResponse(blog));
        }
        return blogResponses;
    }

    @Override
    public void viewIncrement(Long id) {
        Blog blog = blogRepository.findBlogById(id);
        blog.setViews(blog.getViews()+1);
        blogRepository.save(blog);
    }

    @Override
    public List<BlogResponse> findBlogTopView(Long id) {
        List<Blog> blogs = blogRepository.findBlogTopView(id);
        List<BlogResponse> blogResponses = new ArrayList<>();
        blogs.forEach(blog -> blogResponses.add(modelMapper.blogToResponse(blog)));
        return blogResponses;
    }

    @Override
    public BlogResponse apiCreateBlog(BlogRequest blogRequest) {
        Blog blog = Blog.builder()
                .title(blogRequest.getTitle())
                .shortDescription(blogRequest.getShortDescription())
                .content(blogRequest.getContent())
                .thumbnail(blogRequest.getThumbnail())
                .views(0)
                .build();
        blog.setCreatedAt(new Date());
        blog.setCreatedBy(SecurityUtils.getPrinciple().getFullName());
        blogRepository.save(blog);
        return modelMapper.blogToResponse(blog);
    }

    @Override
    public BlogResponse apiUpdateBlog(BlogRequest blogRequest, Long id) {
        Blog blog = blogRepository.findBlogById(id);
        BeanUtils.copyProperties(blogRequest, blog);
        blog.setThumbnail(blogRequest.getThumbnail());
        blog.setModifiedAt(new Date());
        blog.setModifiedBy(SecurityUtils.getPrinciple().getUsername());
        blogRepository.save(blog);
        return modelMapper.blogToResponse(blog);
    }
}
