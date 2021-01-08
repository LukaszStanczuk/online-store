package com.onlinestore.product;

import com.onlinestore.author.Author;
import com.onlinestore.category.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private String pictureOfProduct;
    private double price;
    private ProductType productType;
    @ManyToOne
    private Category category;
    @ManyToMany
    private List<Author> authors;

}
