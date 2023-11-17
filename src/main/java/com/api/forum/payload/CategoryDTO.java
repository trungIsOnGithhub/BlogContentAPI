package com.api.forum.payload;

import jakarta.validation.constraints.NotEmpty;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.Getter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {
    private Long id;
    
    @NotEmpty
    private String name;
    
    @NotEmpty
    private String description;
}