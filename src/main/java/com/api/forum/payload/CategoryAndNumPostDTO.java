package com.api.forum.payload;

import jakarta.persistence.ColumnResult;
import jakarta.persistence.ConstructorResult;
import jakarta.persistence.SqlResultSetMapping;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SqlResultSetMapping(
    name = "get_num_post_by_category_for_targeted_user_mapping",
    classes = @ConstructorResult(
        targetClass = CategoryAndNumPostDTO.class,
        columns = {
            @ColumnResult(name = "categoryName", type = String.class),
            @ColumnResult(name = "postCount", type = Long.class)
        }
    )
)
public class CategoryAndNumPostDTO {
    private String categoryName;
    private Long postCount;
}
