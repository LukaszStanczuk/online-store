package com.onlinestore.category;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @AllArgsConstructor
    static class Categories {
        private List<CategoryDTO> categories;
    }

    @PostMapping("/categories")
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryDTO createCategory(@Valid @RequestBody CategoryDTO categoryDTO) {
        return categoryService.createCategory(categoryDTO);

    }
    @GetMapping("/categories")
    public Categories getCategories() {
        return new Categories(categoryService.getAllCategories());

    }

    @PutMapping("categories/{id}")
    public CategoryDTO editById(@RequestBody CategoryDTO categoryDTO, @PathVariable Long id) {
        return categoryService.editById(categoryDTO,id);
    }
}



