package com.onlinestore.category;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    @AllArgsConstructor
    static class Categories {
        private List<CategoryDTO> categories;
    }

    @PostMapping("/categories")
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryDTO createCategory(@RequestBody CategoryDTO categoryDTO) {
        String name = categoryDTO.getName();
        String parentCategory = categoryDTO.getParentCategory();
        Category category = categoryService.createCategory(name, parentCategory);
        return categoryMapper.mapToCategoryDto(category);

    }

    @GetMapping("/categories")
    public Categories getCategories() {
        return new Categories(categoryService.getAllCategories().stream()
                .map(categoryMapper::mapToCategoryDto)
                .collect(Collectors.toList()));

    }
    // todo możliwość przeciągania kategorii (zmiany położenia) - czy tak to ma wygladac

    @PutMapping("categories/{id}")
    public CategoryDTO editById(@RequestBody CategoryDTO categoryDTO, @PathVariable Long id) {
        Category category = categoryMapper.mapToCategory(categoryDTO);
        Category category1 = categoryService.editById(category, id);
        return categoryMapper.mapToCategoryDto(category1);
    }
}



