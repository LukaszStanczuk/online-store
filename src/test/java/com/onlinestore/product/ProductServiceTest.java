package com.onlinestore.product;

import com.onlinestore.author.Author;
import com.onlinestore.category.Category;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {
    @Mock
    ProductRepository productRepository;
    @Mock
    ProductMapper productMapper;
    @InjectMocks
    ProductService productService;

    @Test
    void createProduct_savesProductInTheRepository() {
        // given
        when(productRepository.save(any(Product.class))).thenReturn(new Product());
        when(productMapper.mapToProductDto(any(Product.class))).thenReturn(new ProductDto());
        // when
        ProductDto product = productService.createProduct(ProductDto.builder()
                .id(null)
                .description("opis")
                .price(20.00).title("tytuł")
                .pictureOfProduct("zdjęcie")
                .category(new Category())
                .authors(List.of(new Author()))
                .build());

        // then
        assertThat(product).isInstanceOf(ProductDto.class);
        verify(productRepository).save(any(Product.class));
    }
}


