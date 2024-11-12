package com.example.fdwproject2.controller;

import com.example.fdwproject2.DTO.TaskDTO;
import com.example.fdwproject2.service.CategoryService;
import com.example.fdwproject2.service.TagService;
import com.example.fdwproject2.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class IndexController {

    private final TaskService taskService;
    private final CategoryService categoryService;
    private final TagService tagService;

    @GetMapping
    public String home(Model model) {
        model.addAttribute("tasks", taskService.getAllTasksWithRelations());
        return "tasks/list";
    }

}