package com.api.forum.payload;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CategoryDTO {
    private Long id;
    
    @NotEmpty
    private String name;
    
    @NotEmpty
    private String description;
}