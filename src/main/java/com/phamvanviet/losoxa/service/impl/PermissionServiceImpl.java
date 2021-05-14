package com.phamvanviet.losoxa.service.impl;

import com.phamvanviet.losoxa.entity.Permission;
import com.phamvanviet.losoxa.mapper.Converter;
import com.phamvanviet.losoxa.mapper.ModelMapper;
import com.phamvanviet.losoxa.model.request.page.PageRequest;
import com.phamvanviet.losoxa.model.request.permission.PermissionRequest;
import com.phamvanviet.losoxa.model.response.PermissionResponse;
import com.phamvanviet.losoxa.repository.PermissionRepository;
import com.phamvanviet.losoxa.service.PermissionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private Converter converter;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<PermissionResponse> findAll() {
        List<Permission> permissionEntities = permissionRepository.findAll();
        List<PermissionResponse> permissionResponses = new ArrayList<>();
        for (Permission permissionEntity : permissionEntities){
            permissionResponses.add(modelMapper.permissionToResponse(permissionEntity));
        }
        return permissionResponses;
    }

    @Override
    public List<PermissionResponse> findByLevel1() {
        List<Permission> permissionEntities = permissionRepository.findByLevel1();
        List<PermissionResponse> permissionResponses = new ArrayList<>();
        for (Permission permissionEntity : permissionEntities){
            permissionResponses.add(modelMapper.permissionToResponse(permissionEntity));
        }
        return permissionResponses;

    }

    @Override
    public List<PermissionResponse> getPagePermission(int offset, int limit) {
        PageRequest pageRequest = new PageRequest(offset, limit, Sort.by("id").descending());
        List<Permission> permissionEntities = permissionRepository.getAll(pageRequest);
        List<PermissionResponse> permissionResponses = new ArrayList<>();
        for (Permission permissionEntity : permissionEntities){
            permissionResponses.add(modelMapper.permissionToResponse(permissionEntity));
        }
        return permissionResponses;
    }

    @Override
    public int getCountAllPermission() {
        return (int)permissionRepository.count();
    }

    @Override
    public PermissionResponse findById(Long id) {
        return modelMapper.permissionToResponse(permissionRepository.getOne(id));
    }

    @Override
    public List<PermissionResponse> findPermissionNotId(Long id) {
        List<Permission> permissionEntities = permissionRepository.findPermissionNotById(id);
        List<PermissionResponse> permissionResponses = new ArrayList<>();
        for (Permission permissionEntity : permissionEntities){
            permissionResponses.add(modelMapper.permissionToResponse(permissionEntity));
        }
        return permissionResponses;
    }

    @Override
    public List<String> convertPermissionToString(Collection<GrantedAuthority> authorities) {
        List<String> permission = new ArrayList<>();
        for (GrantedAuthority grantedAuthority : authorities){
            permission.add(grantedAuthority.toString());
        }
        return permission;
    }

    @Override
    public void deletePermission(Long id) {
        permissionRepository.deleteById(id);
    }


    @Override
    public PermissionResponse apiCreatePermission(PermissionRequest permissionRequest) {
        Permission permissionEntity = new Permission();
        permissionEntity.setName(permissionRequest.getName());
        permissionEntity.setDescription(permissionRequest.getDescription());
        permissionEntity.setParent(permissionRepository.getOne(permissionRequest.getParentId()));
        permissionRepository.save(permissionEntity);
        return modelMapper.permissionToResponse(permissionEntity);
    }

    @Override
    public PermissionResponse apiUpdatePermission(PermissionRequest permissionRequest, Long id) {
        Permission permissionEntity = permissionRepository.getOne(id);
        BeanUtils.copyProperties(permissionRequest, permissionEntity);
        permissionEntity.setParent(permissionRepository.getOne(permissionRequest.getParentId()));
        permissionRepository.save(permissionEntity);
        return modelMapper.permissionToResponse(permissionEntity);
    }
}
