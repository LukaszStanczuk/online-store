package com.onlinestore.category;

import com.onlinestore.exception.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@RequiredArgsConstructor

public class CategoryService {
    private final CategoryRepository categoryRepository;

    Category createCategory(String name, String parentCategory, String childCategory) {
        if (name.isEmpty()){
            throw new BadRequestException("Category cannot be empty");
        }
        Category category = new Category();
        category.setName(name);
        category.setParentCategory(parentCategory);
        category.setChildCategory(childCategory);
        return categoryRepository.save(category);
    }

    public List<Category> getAllCategories() {
      return categoryRepository.findAll();
    }
}
