package com.example.fdwproject2.controller;

import com.example.fdwproject2.model.TaskModel;
import com.example.fdwproject2.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    @GetMapping
    public String index(Model model) {
        List<TaskModel> tasks = taskRepository.findAll();
        model.addAttribute("tasks", tasks);
        return "task-list";
    }

    @GetMapping("/create")
    public String create() {
        return "task-create";
    }

    @PostMapping
    public String store(@RequestParam String name) {
        TaskModel task = new TaskModel(name);
        taskRepository.save(task);
        return "redirect:/tasks";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") Long id, Model model) {
        TaskModel task = taskRepository.findById(id).orElse(null);
        model.addAttribute("task", task);
        return "task-show";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") Long id, Model model) {
        TaskModel task = taskRepository.findById(id).orElse(null);
        model.addAttribute("task", task);
        return "task-edit";
    }

    @PostMapping("/{id}/update")
    public String update(@PathVariable("id") Long id, @RequestParam String name) {
        TaskModel task = taskRepository.findById(id).orElse(null);
        if (task != null) {
            task.setName(name);
            taskRepository.save(task);
        }
        return "redirect:/tasks";
    }

    @PostMapping("/{id}/delete")
    public String destroy(@PathVariable("id") Long id) {
        taskRepository.deleteById(id);
        return "redirect:/tasks";
    }
}
