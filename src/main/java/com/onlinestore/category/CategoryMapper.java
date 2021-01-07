package com.onlinestore.category;

import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {
    CategoryDTO mapToCategoryDto(Category category) {
        return CategoryDTO.builder()
                .name(category.getName())
                .childCategory(category.getChildCategory())
                .parentCategory(category.getParentCategory())
                .build();
    }

}
