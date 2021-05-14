package com.phamvanviet.losoxa.service.impl;

import com.phamvanviet.losoxa.constant.RoleConstant;
import com.phamvanviet.losoxa.entity.User;
import com.phamvanviet.losoxa.mapper.Converter;
import com.phamvanviet.losoxa.model.request.page.PageRequest;
import com.phamvanviet.losoxa.model.request.user.UserCreateRequest;
import com.phamvanviet.losoxa.model.request.user.UserRegisterRequest;
import com.phamvanviet.losoxa.model.request.user.UserUpdateRequest;
import com.phamvanviet.losoxa.model.response.UserResponse;
import com.phamvanviet.losoxa.repository.RoleRepository;
import com.phamvanviet.losoxa.repository.UserRepository;
import com.phamvanviet.losoxa.service.UserService;
import com.phamvanviet.losoxa.util.SecurityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private Converter converter;

    @Override
    public List<UserResponse> getUserAndSort(int offset, int limit) {
        PageRequest pageable = new PageRequest(offset, limit, Sort.by("id").descending());
        List<User> users = userRepository.findUserList(pageable);
        List<UserResponse> userResponses = new ArrayList<>();
        for (User user: users){
            userResponses.add(converter.userToResponse(user));
        }
        return userResponses;
    }

    @Override
    public int getCount() {
        return (int) userRepository.count();
    }

    @Override
    public void createUser(UserCreateRequest userCreateRequest) {
        User user = new User();
        BeanUtils.copyProperties(userCreateRequest, user);

        user.setRole(roleRepository.findRoleEntitiesById(userCreateRequest.getRoleId()));
        user.setStatus(true);
        user.setPassword(passwordEncoder.encode(userCreateRequest.getPassword()));
        user.setCreatedBy(SecurityUtils.getPrinciple().getUsername());
        user.setCreatedAt(new Date());
        user.setPoint(0);
        userRepository.save(user);
    }

    @Override
    public UserResponse findUserById(Long id) {
        User user = userRepository.findUserById(id);
        return converter.userToResponse(user);
    }

    @Override
    public void updateUser(UserUpdateRequest userUpdateRequest, Long id) {
        User user = userRepository.findUserById(id);
        String password = user.getPassword();
        BeanUtils.copyProperties(userUpdateRequest, user);
        user.setPassword(password);
        user.setRole(roleRepository.findRoleEntitiesById(userUpdateRequest.getRoleId()));
        userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        User user = userRepository.findUserById(id);
        user.setStatus(false);
        userRepository.save(user);
    }

    @Override
    public boolean register(UserRegisterRequest userRegisterRequest) {
        if (userRepository.findByUserName(userRegisterRequest.getUserName()) == null && userRepository.findUserByEmail(userRegisterRequest.getEmail()) == null) {
            User user = new User();
            BeanUtils.copyProperties(userRegisterRequest, user);
            user.setPassword(passwordEncoder.encode(userRegisterRequest.getPassword()));
            user.setRole(roleRepository.findRoleByName(RoleConstant.ROLE_USER));
            user.setStatus(true);
            user.setPoint(0);
            user.setCreatedAt(new Date());
            userRepository.save(user);
            return true;
        }
        return false;
    }

    @Override
    public Map<Integer, Integer> statistical() {
        Map<Integer, Integer> statistical = new HashMap<>();
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM");
        int month = Integer.parseInt(simpleDateFormat.format(date).toUpperCase());
        simpleDateFormat = new SimpleDateFormat("YYYY");
        int year = Integer.parseInt(simpleDateFormat.format(date).toUpperCase());
        int index = 0;
        for (int i = month-1; i>=0; i--) {
            int count = userRepository.countUserByMonth(month - i, year);
            index++;
            statistical.put(index, count);
        }
        return statistical;
    }

    @Override
    public boolean emailValid(String email) {
        return (userRepository.findUserEmail(email) == null);
    }

    @Override
    public boolean usernameValid(String username) {
        return (userRepository.findUserUsername(username) == null);
    }
}
