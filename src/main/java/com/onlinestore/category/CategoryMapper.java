package com.onlinestore.category;

import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {
    CategoryDTO mapToCategoryDto(Category category) {
        return CategoryDTO.builder()
                .id(category.getId())
                .name(category.getName())
                .parentCategory(category.getParentCategory())
                .build();
    }

   Category mapToCategory(CategoryDTO categoryDTO) {
        return Category.builder()
                .id(categoryDTO.getId())
                .name(categoryDTO.getName())
                .parentCategory(categoryDTO.getParentCategory())
                .build();
    }
}
