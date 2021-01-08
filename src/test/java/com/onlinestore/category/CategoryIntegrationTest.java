package com.onlinestore.category;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
public class CategoryIntegrationTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    CategoryRepository categoryRepository;

    ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        categoryRepository.deleteAll();
    }

    @Test
    void createLocalization_returnsLocalizationsAnd200StatusCode() throws Exception {
        // given
        CategoryDTO requestBody = new CategoryDTO("auto","motoryzacja","skoda");
        MockHttpServletRequestBuilder request = post("/category")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestBody));

        // when
        MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        CategoryDTO responseBody = objectMapper.readValue(response.getContentAsString(StandardCharsets.UTF_8), CategoryDTO.class);
        assertThat(responseBody).extracting(CategoryDTO::getName, CategoryDTO::getChildCategory).containsExactly("auto", "skoda");
        assertThat(categoryRepository.findAll()).singleElement().satisfies(category -> {
            assertThat(category.getName()).isEqualTo("auto");
            assertThat(category.getChildCategory()).isEqualTo("skoda");
            assertThat(category.getParentCategory()).isEqualTo("motoryzacja");

        });
    }
}