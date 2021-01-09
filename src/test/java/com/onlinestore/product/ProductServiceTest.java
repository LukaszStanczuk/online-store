package com.onlinestore.product;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {
    @Mock
    ProductRepository productRepository;
    @InjectMocks
    ProductService productService;

    @Test
    void createProduct_savesProductInTheRepository() {
        // given
        when(productRepository.save(any(Product.class))).thenReturn(new Product());

        // when
        Product product = productService.createProduct(new ProductDto());

        // then
        assertThat(product).isInstanceOf(Product.class);
        verify(productRepository).save(any(Product.class));
}
}
