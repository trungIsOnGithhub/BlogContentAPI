package com.api.forum.service;

import com.api.forum.payload.LoginDTO;
import com.api.forum.payload.RegisterDto;

public interface AuthService {
    public String login(LoginDTO loginDTO);
    public String register(RegisterDTO registerDTO);
}