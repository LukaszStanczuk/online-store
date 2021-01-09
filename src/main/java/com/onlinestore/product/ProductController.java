package com.onlinestore.product;


import com.onlinestore.category.CategoryDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor

public class ProductController {

    private final ProductService productService;
    private final ProductMapper productMapper;

    @PostMapping("/products")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDto createProduct(@RequestBody ProductDto productDto){
        Product product = productService.createProduct(productDto);
        return productMapper.mapToProductDto(product);
    }
    @GetMapping("/products")
    public List<ProductDto> getAllProducts(){
    return productService.getAllProducts().stream()
            .map(productMapper::mapToProductDto)
            .collect(Collectors.toList());
    }
    @GetMapping("/products/{id}")
    public ProductDto getById(@PathVariable Long id){
        Product product = productService.getById(id);
        return productMapper.mapToProductDto(product);
    }
    @PutMapping("products/{id}")
    public ProductDto editById(@RequestBody ProductDto productDto, @PathVariable Long id){
        Product product = productService.editById(productDto, id);
        return productMapper.mapToProductDto(product);
    }
}




