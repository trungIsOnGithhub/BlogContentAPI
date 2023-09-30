package com.api.forum.controller;

import com.api.forum.payload.AuthJWT;
import com.api.forum.payload.LoginDTO;
import com.api.forum.payload.RegisterDTO;
import com.api.forum.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth")
public class AuthController {
    private AuthService service;

    public AuthController(AuthService service) {
        this.service = service;
    }

    @PostMapping(value = {"/login", "/signin"})
    public ResponseEntity<AuthJWT> login(@RequestBody LoginDTO dto){
        String token = authService.login(dto);

        AuthJWT authJWT = new AuthJWT();
        authJWT.setAccessToken(token);

        return ResponseEntity<AuthJWT>(authJWT, HttpStatus.OK);
    }

    @PostMapping(value = {"/register", "/signup"})
    public ResponseEntity<String> register(@RequestBody RegisterDTO dto){
        String response = authService.register(dto);
        return new ResponseEntity<String>(response, HttpStatus.CREATED);
    }
}