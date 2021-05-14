package com.phamvanviet.losoxa.repository;

import com.phamvanviet.losoxa.entity.District;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DistrictRepository extends JpaRepository<District, Long> {
    List<District> findAllByProvinceId(Long id);
}
