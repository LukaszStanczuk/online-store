package com.onlinestore.category;

import com.onlinestore.exception.BadRequestException;
import com.onlinestore.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Component
@RequiredArgsConstructor
@Transactional

public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoryDto createCategory(CategoryDto categoryDTO) {
        Category category = new Category();
        category.setName(categoryDTO.getName());
        category.setParentCategory(categoryDTO.getParentCategory());
        return categoryMapper.mapToCategoryDto(categoryRepository.save(category));
    }

    public List<CategoryDto> getAllCategories() {
        return categoryRepository.findAll().stream()
                .map(categoryMapper::mapToCategoryDto)
                .collect(Collectors.toList());
    }

    public CategoryDto editById(CategoryDto category, Long id) {
        Category editedCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Category with id: " + id + "not found"));
        editedCategory.setParentCategory(category.getParentCategory());
        return categoryMapper.mapToCategoryDto(categoryRepository.save(editedCategory));

    }
}


