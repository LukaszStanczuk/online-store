package com.onlinestore.product;

import com.onlinestore.category.Category;
import com.onlinestore.exception.BadRequestException;
import com.onlinestore.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
@Transactional

public class ProductService {

    private final ProductRepository productRepository;

    public Product createProduct(ProductDto productDefinition) {
        if (productDefinition == null){
            throw new BadRequestException("No data to create a product");
        }
        Product product = new Product();
        product.setAuthors(productDefinition.getAuthors());
        product.setCategory(productDefinition.getCategory());
        product.setDescription(productDefinition.getDescription());
        product.setPictureOfProduct(productDefinition.getPictureOfProduct());
        product.setPrice(productDefinition.getPrice());
        product.setProductType(productDefinition.getProductType());
        product.setTitle(productDefinition.getTitle());
        return productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(()->new NotFoundException("Product with "+ id + "not found "));
    }


    public Product editById(ProductDto product,Long id) {
        Product editedProduct = productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Product with id " + id + "not found "));
        editedProduct.setTitle(product.getTitle());
        editedProduct.setPrice(product.getPrice());
        editedProduct.setProductType(product.getProductType());
        editedProduct.setPictureOfProduct(product.getPictureOfProduct());
        editedProduct.setDescription(product.getDescription());
        editedProduct.setCategory(product.getCategory());
        editedProduct.setAuthors(product.getAuthors());
        return productRepository.save(editedProduct);
    }
}

