package com.example.examen.controller;

import com.example.examen.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.example.examen.model.MenuItem;
import com.example.examen.model.MenuItem;

import java.util.List;

@RestController
@RequestMapping("/api/menu")
public class MenuController {
    @Autowired
    private MenuService menuService;

    @GetMapping
    public List<MenuItem> getAllMenuItems() {
        return menuService.getAllMenuItems();
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public MenuItem addMenuItem(@RequestBody MenuItem item) {
        return menuService.addMenuItem(item);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteMenuItem(@PathVariable Long id) {
        menuService.deleteMenuItem(id);
    }
}