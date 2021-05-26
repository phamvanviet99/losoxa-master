package com.phamvanviet.losoxa.repository;

import com.phamvanviet.losoxa.entity.Rate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RateRepository extends JpaRepository<Rate, Long> {

    @Query(value = "select r from Rate r where r.product.id = ?1")
    List<Rate> listRateByProduct(Long productId);
}
