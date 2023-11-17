package com.api.forum.payload;

import com.api.forum.entity.User;

import io.swagger.v3.oas.annotations.media.Schema;
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
    private long id;

	@NotEmpty
	@Size(min = 16, message = "Name must be minimum 5 characters")
	private String name;

	@Email
	private String email;

	@NotEmpty
	@Size(min = 16, message = "Comment content must be minimum 16 characters")
	private String content;

	// @NotEmpty
	// private User owner;
}