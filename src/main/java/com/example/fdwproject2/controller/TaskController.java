package com.example.fdwproject2.controller;

import com.example.fdwproject2.DTO.TaskDTO;
import com.example.fdwproject2.service.CategoryService;
import com.example.fdwproject2.service.TagService;
import com.example.fdwproject2.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;
    private final CategoryService categoryService;
    private final TagService tagService;

    @GetMapping
    public String listTasks(Model model) {
        model.addAttribute("tasks", taskService.getAllTasksWithRelations());
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("tags", tagService.getAllTags());
        return "tasks/list";
    }

    @PostMapping
    public String createTask(@RequestParam String title,
                             @RequestParam String description,
                             @RequestParam(required = false) Long categoryId,
                             @RequestParam(required = false) Set<Long> tagIds,
                             RedirectAttributes redirectAttributes) {
        TaskDTO taskDTO = TaskDTO.builder()
                .title(title)
                .description(description)
                .categoryId(categoryId)
                .tagIds(tagIds != null ? tagIds : new HashSet<>())
                .build();
        taskService.createTask(taskDTO);
        redirectAttributes.addFlashAttribute("successMessage", "Task created successfully!");
        return "redirect:/tasks";
    }

    @PostMapping("/{id}")
    public String updateTask(@PathVariable Long id,
                             @RequestParam String title,
                             @RequestParam String description,
                             @RequestParam(required = false) Long categoryId,
                             @RequestParam(required = false) Set<Long> tagIds,
                             RedirectAttributes redirectAttributes) {
        TaskDTO taskDTO = TaskDTO.builder()
                .id(id)
                .title(title)
                .description(description)
                .categoryId(categoryId)
                .tagIds(tagIds != null ? tagIds : new HashSet<>())
                .build();
        taskService.updateTask(id, taskDTO);
        redirectAttributes.addFlashAttribute("successMessage", "Task updated successfully!");
        return "redirect:/tasks";
    }

    @PostMapping("/{id}/delete")
    public String deleteTask(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        taskService.deleteTask(id);
        redirectAttributes.addFlashAttribute("successMessage", "Task deleted successfully!");
        return "redirect:/tasks";
    }
}