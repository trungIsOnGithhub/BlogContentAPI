package com.api.forum.payload;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDTO {
    private Long id;

	@Email
	@NotEmpty
	private String email;
	
	@NotEmpty
	@Size(min = 16, message = "Comment content must be minimum 16 characters")
	private String content;
}