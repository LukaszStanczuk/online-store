package com.onlinestore.category;

import com.onlinestore.product.Product;
import com.onlinestore.product.ProductDto;
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
class CategoryServiceTest {

    @Mock
    CategoryRepository categoryRepository;
    @Mock
    CategoryMapper categoryMapper;
    @InjectMocks
    CategoryService categoryService;

    @Test
    void createCategory_savesCategoryInTheRepository() {
        // given

        when(categoryRepository.save(any(Category.class))).thenReturn(new Category());
        when(categoryMapper.mapToCategoryDto(any(Category.class))).thenReturn(new CategoryDto());

        // when
        CategoryDto categoryDto = categoryService.createCategory(CategoryDto.builder()
                .id(null)
                .parentCategory("kat")
                .name("moja kat")
                .build());

        // then
        assertThat(categoryDto).isInstanceOf(CategoryDto.class);
        verify(categoryRepository).save(any(Category.class));
}
}

