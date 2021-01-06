package com.onlinestore.category;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryDTO {

    private String name;
    private String parentCategory;
    private String childCategory;
}
