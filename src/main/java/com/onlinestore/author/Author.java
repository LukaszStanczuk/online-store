package com.onlinestore.author;

import com.onlinestore.product.Product;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@RequiredArgsConstructor

public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToMany
    private List<Product> products;

}
