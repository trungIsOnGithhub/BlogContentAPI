package com.api.forum.payload;

import lombok.Data;
import lombok.AllArgsConstructor;

import java.util.Date;

import jakarta.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
public class ExceptionDTO {
	@NotEmpty
    private Date timestamp;
	
	@NotEmpty
    private String message;
    private String details;
}
