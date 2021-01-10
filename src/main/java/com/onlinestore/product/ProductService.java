package com.onlinestore.product;

import com.onlinestore.category.Category;
import com.onlinestore.exception.BadRequestException;
import com.onlinestore.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Transactional

public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    // return ProductDto
    public Product createProduct(ProductDto productDto) {
        // if do wywalenia
        if (productDto == null) {
            throw new BadRequestException("No data to create a product");
        }
        Product product = new Product();
        product.setAuthors(productDto.getAuthors());
        product.setCategory(productDto.getCategory());
        product.setDescription(productDto.getDescription());
        product.setPictureOfProduct(productDto.getPictureOfProduct());
        product.setPrice(productDto.getPrice());
        product.setTitle(productDto.getTitle());
        return productRepository.save(product);
    }

    public List<ProductDto> getAllProducts() {
        return productRepository.findAll().stream()
            .map(productMapper::mapToProductDto)
            .collect(Collectors.toList());
    }

    // return ProductDto
    public Product getById(Long id) {
        return productRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("Product with " + id + "not found "));
    }

    // return ProductDto
    public Product editById(ProductDto product, Long id) {
        Product editedProduct = productRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("Product with id " + id + "not found "));
        editedProduct.setTitle(product.getTitle());
        editedProduct.setPrice(product.getPrice());
        editedProduct.setPictureOfProduct(product.getPictureOfProduct());
        editedProduct.setDescription(product.getDescription());
        editedProduct.setCategory(product.getCategory());
        editedProduct.setAuthors(product.getAuthors());
        return editedProduct;
    }
}

