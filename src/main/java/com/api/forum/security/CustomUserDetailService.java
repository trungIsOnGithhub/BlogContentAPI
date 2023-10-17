package com.api.forum.security;

import com.api.forum.repository.UserReposi;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailService implements UserDetailsService {
    private UserReposi repository;

    public CustomUserDetailService(UserReposi userRepository) {
        this.repository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        com.api.forum.entity.User user = this.repository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
                 .orElseThrow( () -> new UsernameNotFoundException("User not found with username or email: "+ usernameOrEmail) );

        Set<GrantedAuthority> authorities = user.getRoles().stream()
                                                .map( (role) -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toSet() );

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
    }
}