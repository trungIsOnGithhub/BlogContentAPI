package com.api.forum.service.implementation;

import com.api.forum.entity.Role;
import com.api.forum.entity.User;
import com.api.forum.exception.types.APIException;
import com.api.forum.payload.LoginDTO;
import com.api.forum.payload.RegisterDTO;
import com.api.forum.repository.RoleReposi;
import com.api.forum.repository.UserReposi;
import com.api.forum.security.JWTProvider;
import com.api.forum.service.AuthService;

import java.util.HashSet;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    private AuthenticationManager authenticationManager;
    private UserReposi userReposi;
    private RoleReposi roleReposi;
    private PasswordEncoder passwordEncoder;
    private JWTProvider jwtTokenProvider;


    public AuthServiceImpl(AuthenticationManager authenticationManager,
                           UserReposi userReposi,
                           RoleReposi roleReposi,
                           PasswordEncoder passwordEncoder,
                           JWTProvider jwtTokenProvider) {
        this.userReposi = userReposi;
        this.roleReposi = roleReposi;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public String login(LoginDTO dto) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken( dto.getUsernameOrEmail(), dto.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtTokenProvider.generateToken(authentication);

        return token;
    }

    @Override
    public String register(RegisterDTO registerDTO) {
        if(userReposi.existsByUsername(registerDTO.getUsername())){
            throw new APIException(HttpStatus.BAD_REQUEST, "Username is already exists!.");
        }

        if(userReposi.existsByEmail(registerDTO.getEmail())){
            throw new APIException(HttpStatus.BAD_REQUEST, "Email is already exists!.");
        }

        User user = new User();
        user.setName(registerDTO.getName());
        user.setUsername(registerDTO.getUsername());
        user.setEmail(registerDTO.getEmail());
        user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));

        Set<Role> roles = new HashSet<>();
        Role userRole = roleReposi.findByName("ROLE_USER").get();
        roles.add(userRole);
        user.setRoles(roles);

        userReposi.save(user);

        return "User registered successfully!.";
    }
}
