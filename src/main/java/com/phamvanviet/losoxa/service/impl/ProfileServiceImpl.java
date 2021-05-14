package com.phamvanviet.losoxa.service.impl;

import com.phamvanviet.losoxa.entity.User;
import com.phamvanviet.losoxa.repository.UserRepository;
import com.phamvanviet.losoxa.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ProfileServiceImpl implements ProfileService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void updateProfile(Long id, String fullName, String phone, String address) {
        User user = userRepository.findUserById(id);
        user.setFullName(fullName);
        user.setPhone(phone);
        user.setAddress(address);
        user.setModifiedAt(new Date());
        userRepository.save(user);
    }
}
