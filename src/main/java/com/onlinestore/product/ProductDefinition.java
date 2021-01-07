package com.onlinestore.product;

import com.onlinestore.author.Author;
import com.onlinestore.category.Category;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter

public class ProductDefinition {

    private String title;
    private String description;
    private String pictureOfProduct;
    private double price;
    private ProductType productType;
    private Category category;
    private List<Author>authors;
}
