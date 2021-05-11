package ru.geekbrains.AMorozov.market.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.geekbrains.AMorozov.market.model.Category;

@Data
@NoArgsConstructor
public class CategoryDto {
    private Long id;
    private String title;

    public CategoryDto(Category category) {
        id = category.getId();
        title = category.getTitle();
    }
}
