package com.example.fdwproject2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/tasks")
public class TaskController {

    @GetMapping
    public String index() {
        return "tasks/index";
    }

    @GetMapping("/create")
    public String create() {
        return "tasks/create";
    }

    @PostMapping
    public String store() {
        return "redirect:/tasks";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable int id) {
        return "tasks/show";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable int id) {
        return "tasks/edit";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable int id) {
        return "redirect:/tasks";
    }

    @DeleteMapping("/{id}")
    public String destroy(@PathVariable int id) {
        return "redirect:/tasks";
    }
}
