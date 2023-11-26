package com.api.forum.controller;

import com.api.forum.payload.AuthJWT;
import com.api.forum.payload.LoginDTO;
import com.api.forum.payload.RegisterDTO;
import com.api.forum.service.AuthService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/trial/auth")
public class AuthController {
    @Autowired
    private AuthService service;

    @PostMapping(value = {"/login", "/signin"})
    public ResponseEntity<AuthJWT> login(@RequestBody LoginDTO dto){
        String token = this.service.login(dto);

        AuthJWT authJWT = new AuthJWT();
        authJWT.setAccessToken(token);

        return ResponseEntity.ok(authJWT);
    }

    @PostMapping(value = "/signup")
    public ResponseEntity<String> register(@RequestBody RegisterDTO dto){
        String response = this.service.register(dto);
        return new ResponseEntity<String>(response, HttpStatus.CREATED);
    }
}