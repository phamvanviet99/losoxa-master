package com.phamvanviet.losoxa.repository;

import com.phamvanviet.losoxa.entity.Permission;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {
    @Query("select p from Permission p where p.parent is null")
    List<Permission> findByLevel1();

    @Query("select p from Permission p")
    List<Permission> getAll(Pageable pageable);

    @Query("select p from Permission p where p.id <> :id and p.id not in(select c from Permission c where c.parent.id =:id)")
    List<Permission> findPermissionNotById(@Param(value = "id") Long id);
}
