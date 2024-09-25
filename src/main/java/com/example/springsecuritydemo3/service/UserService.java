package com.example.springsecuritydemo3.service;

import com.example.springsecuritydemo3.model.domain.User;
import com.example.springsecuritydemo3.model.security.UserPrinciple;
import com.example.springsecuritydemo3.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    public User save(User newUser) {
        if (newUser.getId() == null) {
            newUser.setCreatedAt(LocalDateTime.now());
        }

        newUser.setUpdatedAt(LocalDateTime.now());
        return userRepository.save(newUser);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserPrinciple userPrinciple = userRepository.findByEmail(username)
                .map(user ->
                        new UserPrinciple( // #principle - 2
                                user.getEmail(),
                                user.getPassword(),
                                user.getFirstName(),
                                user.getLastName(),
                                user.getRoles()))
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        System.out.println(userPrinciple);
        return userPrinciple;
    }
}