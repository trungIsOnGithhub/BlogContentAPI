package com.api.forum.exception.types;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class APIException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 3356588060876624518L;

	private HttpStatus status;
    private String message;

    public APIException(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public APIException(String messageSuper, HttpStatus status, String message) {
        super(messageSuper);
        this.status = status;
        this.message = message;
    }
}