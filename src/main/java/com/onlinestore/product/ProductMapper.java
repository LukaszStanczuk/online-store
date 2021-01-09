package com.onlinestore.product;

import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    ProductDto mapToProductDto(Product product){
        return ProductDto.builder()
                .id(product.getId())
                .category(product.getCategory())
                .description(product.getDescription())
                .pictureOfProduct(product.getPictureOfProduct())
                .price(product.getPrice())
                .productType(product.getProductType())
                .authors(product.getAuthors())
                .title(product.getTitle())
                .build();
    }

    ProductDefinition mapToProductDefinition(ProductDto productDto){
        return ProductDefinition.builder()

                .authors(productDto.getAuthors())
                .category(productDto.getCategory())
                .description(productDto.getDescription())
                .pictureOfProduct(productDto.getPictureOfProduct())
                .price(productDto.getPrice())
                .productType(productDto.getProductType())
                .title(productDto.getTitle())
                .build();
    }
}
