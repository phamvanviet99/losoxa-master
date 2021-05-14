package com.phamvanviet.losoxa.repository;

import com.phamvanviet.losoxa.entity.Role;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findRoleByName(String name);

    Role findRoleEntitiesById(Long id);

    @Query("select u from Role u")
    List<Role> getAll(Pageable pageable);
}
