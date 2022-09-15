package com.miniproject.usermanagement.service.auth;


import com.miniproject.usermanagement.Repository.UserRepository;
import com.miniproject.usermanagement.exceptions.InvalidUserNameOrEmailException;
import com.miniproject.usermanagement.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        Optional<User> user = repository.findByUsernameOrEmail(username, username);
        if (user.isPresent()) {
            List<GrantedAuthority> grantedAuthorities=new ArrayList<>();
            grantedAuthorities.add(new GrantedAuthority() {
                @Override
                public String getAuthority() {
                    return user.get().getRole();
                }
            });
            String userName=username;
            if(user.get().getEmail().equals(userName))userName=user.get().getEmail();
            return new org.springframework.security.core.userdetails.User(userName, user.get().getPassword(), grantedAuthorities);
        } else {
            System.out.println("user not find   "+username);
            throw new InvalidUserNameOrEmailException("Invalid UserName Or Email Exception");
        }
    }
}