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

    @PostMapping("/product")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDto createProduct(@RequestBody ProductDto productDto){
        ProductDefinition productDefinition = productMapper.mapToProductDefinition(productDto);
        Product product = productService.createProduct(productDefinition);
        return productMapper.mapToProductDto(product);
    }
    @GetMapping("/products")
    public List<ProductDto> getAllProducts(){
    return productService.getAllProducts().stream()
            .map(productMapper::mapToProductDto)
            .collect(Collectors.toList());
    }
    @GetMapping("/product/{id}")
    public ProductDto getById(@PathVariable Long id){
        Product product = productService.getById(id);
        return productMapper.mapToProductDto(product);
    }
    @PutMapping("product/{id}")
    public ProductDto editById(@RequestBody ProductDto productDto, @PathVariable Long id){
        ProductDefinition productDefinition = productMapper.mapToProductDefinition(productDto);
        Product product = productService.editById(productDefinition, id);
        return productMapper.mapToProductDto(product);
    }
}




