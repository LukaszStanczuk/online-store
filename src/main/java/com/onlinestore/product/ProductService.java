package com.onlinestore.product;

import com.onlinestore.category.Category;
import com.onlinestore.category.CategoryDto;
import com.onlinestore.category.CategoryMapper;
import com.onlinestore.category.CategoryRepository;
import com.onlinestore.exception.BadRequestException;
import com.onlinestore.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Transactional

public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final CategoryMapper categoryMapper;
    private final CategoryRepository categoryRepository;

    public ProductDto createProduct(ProductDto productDto) {
        // to daje usera SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Product product = new Product();
        product.setAuthors(productDto.getAuthors());
        product.setCategory(productDto.getCategory());
        product.setDescription(productDto.getDescription());
        product.setPictureOfProduct(productDto.getPictureOfProduct());
        product.setPrice(productDto.getPrice());
        product.setTitle(productDto.getTitle());
        Product savedProduct = productRepository.save(product);
        return productMapper.mapToProductDto(savedProduct);
    }

    public List<ProductDto> getAllProducts() {

        return productRepository.findAll().stream()
                .map(productMapper::mapToProductDto)
                .collect(Collectors.toList());
    }

    public ProductDto getById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Product with " + id + "not found "));
        return productMapper.mapToProductDto(product);
    }

    public ProductDto editById(ProductDto product, Long id) {
        Product editedProduct = productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Product with id " + id + "not found "));
        editedProduct.setTitle(product.getTitle());
        editedProduct.setPrice(product.getPrice());
        editedProduct.setPictureOfProduct(product.getPictureOfProduct());
        editedProduct.setDescription(product.getDescription());
        // TODO: fetch whole category from categoryRepository
        //editedProduct.setCategory(product.getCategory());
        editedProduct.setAuthors(product.getAuthors());
        Product savedProduct = productRepository.save(editedProduct);
        return productMapper.mapToProductDto(savedProduct);
    }

    public Page<ProductDto> getPageOfProduct(Integer pageNumber, Integer pageSize, String categoryName) {
        //final Category category = categoryRepository.getOne(categoryName);
        //productRepository.findByCategory(category, PageRequest.of(pageNumber, pageSize));
        Page<Product> page = productRepository.findByCategory(categoryName, PageRequest.of(pageNumber, pageSize));
        return page.map(productMapper::mapToProductDto);
    }

}

