package com.github.aleksanderkot00.onlinesportsbetting.front.Onlinesprotsbettingfront.controller;

import com.github.aleksanderkot00.onlinesportsbetting.front.Onlinesprotsbettingfront.client.CategoryClient;
import com.github.aleksanderkot00.onlinesportsbetting.front.Onlinesprotsbettingfront.domain.dto.CategoryDto;
import com.github.aleksanderkot00.onlinesportsbetting.front.Onlinesprotsbettingfront.domain.dto.NameDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("categories")
public class CategoryController {

    private final CategoryClient categoryClient = new CategoryClient();
    @GetMapping
    public List<CategoryDto> getCategories() {
        return categoryClient.getCategories();
    }

    @GetMapping("/{categoryId}")
    public CategoryDto getCategory(@PathVariable long categoryId) {
        return categoryClient.getCategory(categoryId);
    }

    @PostMapping
    public CategoryDto addCategory(@RequestBody NameDto name) {
        return categoryClient.addCategory(name);
    }

    @PatchMapping("/{categoryId}")
    public CategoryDto changeName(@PathVariable long categoryId, @RequestBody NameDto name) {
        return categoryClient.changeName(categoryId, name);
    }

    @DeleteMapping("/{categoryId}")
    public void deleteCategory(@PathVariable long categoryId) {
        categoryClient.deleteCategory(categoryId);
    }

    @PatchMapping("/{categoryId}/events/{eventId}")
    public CategoryDto addEvent(@PathVariable long categoryId, @PathVariable long eventId) {
        return categoryClient.addEventToCategory(categoryId, eventId);
    }
}