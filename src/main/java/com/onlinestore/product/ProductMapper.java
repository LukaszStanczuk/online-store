package com.onlinestore.product;

import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    ProductDto mapToProductDto(Product product) {
        return ProductDto.builder()
                .id(product.getId())
                .category(product.getCategory())
                .description(product.getDescription())
                .pictureOfProduct(product.getPictureOfProduct())
                .price(product.getPrice())
                .authors(product.getAuthors())
                .title(product.getTitle())
                .build();
    }
}




