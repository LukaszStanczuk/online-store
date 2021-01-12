package com.onlinestore.category;

import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {
    public CategoryDto mapToCategoryDto(Category category) {
        return CategoryDto.builder()
                .id(category.getId())
                .name(category.getName())
                .parentCategory(category.getParentCategory())
                .build();
    }

    public Category mapToCategory(CategoryDto categoryDTO) {
        return Category.builder()
                .id(categoryDTO.getId())
                .name(categoryDTO.getName())
                .parentCategory(categoryDTO.getParentCategory())
                .build();
    }
}
