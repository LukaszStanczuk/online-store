package com.onlinestore.category;

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
    @InjectMocks
    CategoryService categoryService;

    @Test
    void createCategory_savesCategoryInTheRepository() {
        // given
        when(categoryRepository.save(any(Category.class))).thenReturn(new Category());

        // when

        Category category = categoryService.createCategory("Auto", "Motoryzacja", "Skoda");

        // then
        assertThat(category).isInstanceOf(Category.class);
        verify(categoryRepository).save(any(Category.class));
}
}

