package com.phamvanviet.losoxa.repository;

import com.phamvanviet.losoxa.entity.Blog;
import com.phamvanviet.losoxa.entity.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BlogRepository extends JpaRepository<Blog, Long> {
    @Query("select b from Blog b")
    List<Blog> getAll(Pageable pageable);

    Blog findBlogById(Long id);

    @Query("select b from Blog b")
    List<Blog> findAllAndSort(Pageable pageable);

    @Query(nativeQuery = true, value = "select * from Blog b where b.id <> ?1 order by b.views desc limit 4")
    List<Blog> findBlogTopView(Long id);
}
