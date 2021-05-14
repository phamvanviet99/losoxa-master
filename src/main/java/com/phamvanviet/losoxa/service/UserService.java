package com.phamvanviet.losoxa.service;

import com.phamvanviet.losoxa.entity.User;
import com.phamvanviet.losoxa.model.request.user.UserCreateRequest;
import com.phamvanviet.losoxa.model.request.user.UserRegisterRequest;
import com.phamvanviet.losoxa.model.request.user.UserUpdateRequest;
import com.phamvanviet.losoxa.model.response.UserResponse;

import java.util.List;
import java.util.Map;

public interface UserService {
    List<UserResponse> getUserAndSort(int limit, int offset);
    int getCount();
    void createUser(UserCreateRequest userCreateRequest);
    UserResponse findUserById(Long id);
    void updateUser(UserUpdateRequest userUpdateRequest, Long id);
    void deleteUser(Long id);
    boolean emailValid(String email);
    boolean usernameValid(String username);
    boolean register(UserRegisterRequest userRegisterRequest);
    Map<Integer, Integer> statistical();
}
