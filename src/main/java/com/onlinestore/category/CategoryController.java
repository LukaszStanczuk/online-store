package com.onlinestore.category;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @AllArgsConstructor
    static class Categories {
        private List<CategoryDto> categories;
    }

    @PostMapping("/categories")
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryDto createCategory(@Valid @RequestBody CategoryDto categoryDTO) {
        return categoryService.createCategory(categoryDTO);
    }

    @GetMapping("/categories")
    public Categories getCategories() {
        return new Categories(categoryService.getAllCategories());
    }

    @PutMapping("categories/{id}")
    public CategoryDto editById(@RequestBody CategoryDto categoryDTO, @PathVariable Long id) {
        return categoryService.editById(categoryDTO, id);
    }
}



