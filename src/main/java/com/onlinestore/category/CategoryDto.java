package com.onlinestore.category;

import com.onlinestore.annotation.ExistingCategory;
import lombok.*;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryDto {


    private Long id;
    @NotNull
    private String name;
    private String parentCategory;


}
