package ru.geekbrains.AMorozov.market.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.AMorozov.market.dtos.CategoryDto;
import ru.geekbrains.AMorozov.market.error_handling.ResourceNotFoundException;
import ru.geekbrains.AMorozov.market.model.Category;
import ru.geekbrains.AMorozov.market.services.CategoryService;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/categories")
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("/{id}")
    public CategoryDto findOneById(@PathVariable Long id) {
        Category category = categoryService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product doesn't exists id "+id));
        return new CategoryDto(category);
    }
}
