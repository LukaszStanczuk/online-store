package com.onlinestore.category;

import com.onlinestore.exception.BadRequestException;
import com.onlinestore.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Component
@RequiredArgsConstructor

public class CategoryService {
    private final CategoryRepository categoryRepository;

    public Category createCategory(String name, String parentCategory) {
        if (name.isEmpty()) {
            throw new BadRequestException("Category cannot be empty");
        }
        Category category = new Category();
        category.setName(name);
        category.setParentCategory(parentCategory);
        return categoryRepository.save(category);
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Transactional
    public Category editById(Category category, Long id) {
        Category editedCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Category with id: " + id + "not found"));
        editedCategory.setParentCategory(category.getParentCategory());
        return categoryRepository.save(editedCategory);

    }
}


