package com.phamvanviet.losoxa.repository;

import com.phamvanviet.losoxa.entity.Commune;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommuneRepository extends JpaRepository<Commune, Long> {
    List<Commune> findAllByDistrictId(Long id);
}
