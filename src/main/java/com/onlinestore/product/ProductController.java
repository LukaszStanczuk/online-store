package com.onlinestore.product;


import com.onlinestore.category.CategoryDto;
import com.onlinestore.user.User;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor

public class ProductController {

    private final ProductService productService;

    @AllArgsConstructor
    static class Products {
        private List<ProductDto> products;
    }

    @PostMapping("/products")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDto createProduct(@Valid @RequestBody ProductDto productDto, @AuthenticationPrincipal User user) {
        return productService.createProduct(productDto);
    }

    @GetMapping("/products")
    public Products getAllProducts() {
        return new Products(productService.getAllProducts());
    }

    @GetMapping("/products/{id}")
    public ProductDto getById(@PathVariable Long id) {
        return productService.getById(id);
    }

    @PutMapping("products/{id}")
    public ProductDto editById(@RequestBody ProductDto productDto, @PathVariable Long id) {
        return productService.editById(productDto, id);
    }

    @GetMapping(value = "/products", params = {"pageNumber", "pageSize", "categoryName"})
    public Page<ProductDto> getPageOfProducts(@RequestParam(name = "pageNumber") Integer pageNumber,
                                              @RequestParam(name = "pageSize") Integer pageSize,
                                              @RequestParam(name = "categoryName") String categoryName) {
        return productService.getPageOfProduct(pageNumber, pageSize, categoryName);
    }
}




