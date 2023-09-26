package com.springboot.blog.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.Setter;

@Getter
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException{
    private String fieldName;
    private long fieldValue;
    private String resourceName;

    public NotFoundException(String resourceName, String fieldName, long fieldValue) {
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
        this.resourceName = resourceName;
        super( String.format("Resource Not Found: %s with %s : '%s'", resourceName, fieldName, fieldValue) );
    }
}