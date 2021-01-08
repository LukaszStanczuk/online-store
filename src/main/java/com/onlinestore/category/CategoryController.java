package com.onlinestore.category;

import com.onlinestore.product.Product;
import com.onlinestore.product.ProductDefinition;
import com.onlinestore.product.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/category")
    List<CategoryDTO> getCategories(){
        return categoryService.getAllCategories().stream()
                .map(categoryMapper::mapToCategoryDto)
                .collect(Collectors.toList());

    }
   // todo możliwość przeciągania kategorii (zmiany położenia) - czy tak to ma wygladac

    @PutMapping("category/{id}")
    CategoryDTO editById(@RequestBody CategoryDTO categoryDTO, @PathVariable Long id){
        Category category = categoryMapper.mapToCategory(categoryDTO);
        Category category1 = categoryService.editById(category, id);
        return categoryMapper.mapToCategoryDto(category1);
    }
}



