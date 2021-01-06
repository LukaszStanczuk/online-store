package com.onlinestore.category;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    @PostMapping("/category")
    ResponseEntity<CategoryDTO> createCategory(@RequestBody CategoryDTO categoryDTO) {
        String name = categoryDTO.getName();
        String parentCategory = categoryDTO.getParentCategory();
        String childCategory = categoryDTO.getChildCategory();
        Category category = categoryService.createCategory(name, parentCategory, childCategory);
        return ResponseEntity
                .status(HttpStatus.CREATED).body(categoryMapper.mapToCategoryDto(category));

    }
    @GetMapping
    List<CategoryDTO> getCategories(){
        return categoryService.getAllCategories().stream()
                .map(categoryMapper::mapToCategoryDto)
                .collect(Collectors.toList());

    }
   // todo możliwość przeciągania kategorii (zmiany położenia)
}

