package com.api.forum.payload;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginDTO {
	@NotEmpty
    private String usernameOrEmail;
	
	@NotEmpty
    private String password;
}