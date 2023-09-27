package com.api.forum.service;

import com.api.forum.payload.LoginDTO;
import com.api.forum.payload.RegisterDTO;

public interface Auth {
    public String login(LoginDTO loginDTO);
    public String register(RegisterDTO registerDTO);
}