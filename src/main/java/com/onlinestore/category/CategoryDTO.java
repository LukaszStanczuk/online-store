package com.onlinestore.category;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryDTO {

    private String name;
    private String parentCategory;
    private String childCategory;
}
