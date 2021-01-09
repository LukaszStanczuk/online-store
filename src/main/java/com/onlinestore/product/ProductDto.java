package com.onlinestore.product;

import com.onlinestore.author.Author;
import com.onlinestore.category.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor

public class ProductDto {
    private Long id;
    @NotNull
    private String title;
    @NotNull
    private String description;
    @NotNull
    private String pictureOfProduct;
    @NotNull
    private double price;
    private Category category;
    private List<Author> authors;
}
