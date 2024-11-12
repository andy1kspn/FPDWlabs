package com.example.fdwproject2.controller;

import com.example.fdwproject2.DTO.CategoryDTO;
import com.example.fdwproject2.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;

@Controller
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping
    public String listCategories(Model model) {
        model.addAttribute("categories", categoryService.getAllCategories());
        return "categories/list";
    }

    @PostMapping
    public String createCategory(@RequestParam String name, @RequestParam String description) {
        CategoryDTO categoryDTO = CategoryDTO.builder()
                .name(name)
                .description(description)
                .build();
        categoryService.createCategory(categoryDTO);
        return "redirect:/categories";
    }

    @PostMapping("/{id}")
    public String updateCategory(@PathVariable Long id,
                                 @RequestParam String name,
                                 @RequestParam String description) {
        CategoryDTO categoryDTO = CategoryDTO.builder()
                .id(id)
                .name(name)
                .description(description)
                .build();
        categoryService.updateCategory(id, categoryDTO);
        return "redirect:/categories";
    }

    @PostMapping("/{id}/delete")
    public String deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return "redirect:/categories";
    }
}