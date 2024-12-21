package com.example.examen.service;

import com.example.examen.model.MenuCategory;
import com.example.examen.model.MenuItem;
import com.example.examen.repository.MenuCategoryRepository;
import com.example.examen.repository.MenuItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MenuService {
    @Autowired
    private MenuCategoryRepository categoryRepository;

    @Autowired
    private MenuItemRepository menuItemRepository;

    public List<MenuCategory> getAllCategories() {
        return categoryRepository.findAll();
    }

    public List<MenuItem> getAllMenuItems() {
        return menuItemRepository.findAll();
    }

    public MenuItem addMenuItem(MenuItem menuItem) {
         MenuCategory category = categoryRepository.findById(1L)
                .orElseGet(() -> {
                     MenuCategory defaultCategory = new MenuCategory();
                    defaultCategory.setName("Default Category");
                    defaultCategory.setDescription("Default Category Description");
                    return categoryRepository.save(defaultCategory);
                });

        menuItem.setCategory(category);
        return menuItemRepository.save(menuItem);
    }

    public void deleteMenuItem(Long id) {
        menuItemRepository.deleteById(id);
    }
}