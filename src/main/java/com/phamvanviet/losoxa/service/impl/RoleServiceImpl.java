package com.phamvanviet.losoxa.service.impl;

import com.phamvanviet.losoxa.entity.Permission;
import com.phamvanviet.losoxa.entity.Role;
import com.phamvanviet.losoxa.mapper.Converter;
import com.phamvanviet.losoxa.model.request.page.PageRequest;
import com.phamvanviet.losoxa.model.request.role.RoleRequest;
import com.phamvanviet.losoxa.model.response.RoleResponse;
import com.phamvanviet.losoxa.repository.PermissionRepository;
import com.phamvanviet.losoxa.repository.RoleRepository;
import com.phamvanviet.losoxa.service.RoleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private Converter converter;

    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public List<RoleResponse> getPageRole(int offset, int limit) {
        PageRequest pageRequest = new PageRequest(offset, limit, Sort.by("id").descending());
        List<Role> roleEntities = roleRepository.getAll(pageRequest);
        List<RoleResponse> roleResponses = new ArrayList<>();
        for (Role roleEntity : roleEntities) {
            roleResponses.add(converter.roleToResponse(roleEntity));
        }
        return roleResponses;
    }

    @Override
    public int getCountAllRole() {
        return (int) roleRepository.count();
    }

    @Override
    public RoleResponse findById(Long id) {
        return converter.roleToResponse(roleRepository.findRoleEntitiesById(id));
    }

    @Override
    public void deleteRole(Long id) {
        roleRepository.deleteById(id);
    }

    @Override
    public List<String> getPermissionNameByRole(Long id) {
        Role roleEntity = roleRepository.getOne(id);
        List<Permission> permissionEntities = roleEntity.getPermission();
        List<String> permissionNames = new ArrayList<>();
        for (Permission permissionEntity : permissionEntities) {
            permissionNames.add(permissionEntity.getName());
        }
        return permissionNames;
    }


    /*api*/

    @Override
    public RoleResponse apiCreateRole(RoleRequest roleRequest) {
        List<Permission> permissionEntities = new ArrayList<>();
        for (Long id : roleRequest.getPermissionIds()) {
            Permission permissionEntity = permissionRepository.getOne(id);
            permissionEntities.add(permissionEntity);
        }

        Role roleEntity = Role.builder()
                .name(roleRequest.getName())
                .description(roleRequest.getDescription())
                .permission(permissionEntities)
                .build();
        roleRepository.save(roleEntity);
        return converter.roleToResponse(roleEntity);
    }

    //ham cap nhat role
    @Override
    public RoleResponse apiUpdateRole(RoleRequest roleRequest, Long id) {
        //tim role can cap nhat va sao chep no voi du lieu nguoi dung nhap vao
        Role roleEntity = roleRepository.findRoleEntitiesById(id);
        BeanUtils.copyProperties(roleRequest, roleEntity);

        //cap nhat permission
        List<Permission> permissionEntities = new ArrayList<>();
        List<Long> permissionResponseIds = roleRequest.getPermissionIds();
        for (Long permissionId : permissionResponseIds) {
            Permission permissionEntity = permissionRepository.getOne(permissionId);
            permissionEntities.add(permissionEntity);
        }
        roleEntity.setPermission(permissionEntities);
        roleRepository.save(roleEntity);
        return converter.roleToResponse(roleEntity);
    }
}
