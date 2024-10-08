package com.example.fdwproject2.service;

import com.example.fdwproject2.model.TaskModel;
import com.example.fdwproject2.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    public Optional<TaskModel> findById(Long id) {
        return taskRepository.findById(id);
    }

    public TaskModel updateTask(Long id, TaskModel updatedTask) {
        return taskRepository.findById(id).map(task -> {
            task.setName(updatedTask.getName());
            return taskRepository.save(task);
        }).orElse(null);
    }
}