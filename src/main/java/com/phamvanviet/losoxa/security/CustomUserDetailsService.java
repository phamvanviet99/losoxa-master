package com.phamvanviet.losoxa.security;

import com.phamvanviet.losoxa.entity.Permission;
import com.phamvanviet.losoxa.entity.Role;
import com.phamvanviet.losoxa.entity.User;
import com.phamvanviet.losoxa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findOneByUserNameAndStatus(username, true);
        if(user == null){
            throw new UsernameNotFoundException("User not found");
        }
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        Role role = user.getRole();
        grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
        for (Permission permission : role.getPermission()){
            grantedAuthorities.add(new SimpleGrantedAuthority(permission.getName()));
        }

        CustomUserDetails userDetails = new CustomUserDetails(user.getUserName(), user.getPassword(), true,true,true,true, grantedAuthorities);
        userDetails.setId(user.getId());
        userDetails.setFullName(user.getFullName());
        userDetails.setEmail(user.getEmail());
        userDetails.setPoint(user.getPoint());
        return userDetails;
    }
}
