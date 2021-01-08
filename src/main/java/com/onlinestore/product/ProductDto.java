package com.onlinestore.product;

import com.onlinestore.author.Author;
import com.onlinestore.category.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor

public class ProductDto {
    private Long id;
    private String title;
    private String description;
    private String pictureOfProduct;
    private double price;
    private ProductType productType;
    private Category category;
    private List<Author> authors;
}
