package com.api.forum.service.implementation;

import com.api.forum.entity.Role;
import com.api.forum.entity.User;
import com.api.forum.exception.APIException;
import com.api.forum.payload.LoginDTO;
import com.api.forum.payload.RegisterDTO;
import com.api.forum.repository.RoleRepository;
import com.api.forum.repository.UserRepository;
import com.api.forum.security.JWTProvider;
import com.api.forum.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl {
    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private JWTProvider jwtTokenProvider;


    public AuthServiceImpl(AuthenticationManager authenticationManager,
                           UserRepository userRepository,
                           RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder,
                           JWTProvider jwtTokenProvider) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
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
        if(userRepository.existsByUsername(registerDTO.getUsername())){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Username is already exists!.");
        }

        if(userRepository.existsByEmail(registerDTO.getEmail())){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Email is already exists!.");
        }

        User user = new User();
        user.setName(registerDTO.getName());
        user.setUsername(registerDTO.getUsername());
        user.setEmail(registerDTO.getEmail());
        user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));

        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName("ROLE_USER").get();
        roles.add(userRole);
        user.setRoles(roles);

        userRepository.save(user);

        return "User registered successfully!.";
    }
}
