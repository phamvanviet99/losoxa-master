package com.phamvanviet.losoxa.repository;

import com.phamvanviet.losoxa.entity.Category;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query("select p from Category p order by p.name")
    List<Category> findAllByArrangeName();

    @Query("select count(u) from Category u")
    int countAllCategory();

    @Query("select u from Category u")
    List<Category> findCategoryList(Pageable pageable);

    Category findCategoryByName(String name);

    Category findCategoryById(Long id);

    void deleteById(Long id);



}
