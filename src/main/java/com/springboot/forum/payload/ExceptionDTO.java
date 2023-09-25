package com.api.forum.payload;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
public class ExceptionInfo {
    private Date timestamp;
    private String message;
    private String details;
}
