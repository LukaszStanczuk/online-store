package com.onlinestore.product;

import com.onlinestore.annotation.ExistingProduct;
import com.onlinestore.annotation.ExistingUser;
import com.onlinestore.author.Author;
import com.onlinestore.category.Category;
import com.onlinestore.category.CategoryDto;
import com.onlinestore.user.UserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor

public class ProductDto {

    @ExistingProduct
    private Long id;
    @NotNull
    private String title;
    @NotNull
    private String description;
    @NotNull
    private String pictureOfProduct;
    @NotNull
    private double price;

    @Valid
    private CategoryDto category;

    @ExistingUser
    private String username;
}
