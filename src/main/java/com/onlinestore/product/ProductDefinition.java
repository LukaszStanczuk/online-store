package com.onlinestore.product;

import com.onlinestore.author.Author;
import com.onlinestore.category.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class ProductDefinition {

    private String title;
    private String description;
    private String pictureOfProduct;
    private double price;
    private ProductType productType;
    private Category category;
    private List<Author>authors;
}
