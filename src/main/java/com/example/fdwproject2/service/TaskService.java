package com.example.fdwproject2.service;

import com.example.fdwproject2.DTO.CategoryDTO;
import com.example.fdwproject2.DTO.TagDTO;
import com.example.fdwproject2.DTO.TaskDTO;
import com.example.fdwproject2.model.Category;
import com.example.fdwproject2.model.Tag;
import com.example.fdwproject2.model.Task;
import com.example.fdwproject2.repository.CategoryRepository;
import com.example.fdwproject2.repository.TagRepository;
import com.example.fdwproject2.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final CategoryRepository categoryRepository;
    private final TagRepository tagRepository;

    public List<TaskDTO> getAllTasksWithRelations() {
        return taskRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public TaskDTO getTaskById(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        return convertToDTO(task);
    }

    public TaskDTO createTask(TaskDTO taskDTO) {
        Task task = new Task();
        task.setTitle(taskDTO.getTitle());
        task.setDescription(taskDTO.getDescription());

        if (taskDTO.getCategoryId() != null) {
            Category category = categoryRepository.findById(taskDTO.getCategoryId())
                    .orElseThrow(() -> new RuntimeException("Category not found"));
            task.setCategory(category);
        }

        if (taskDTO.getTagIds() != null && !taskDTO.getTagIds().isEmpty()) {
            Set<Tag> tags = tagRepository.findAllById(taskDTO.getTagIds())
                    .stream()
                    .collect(Collectors.toSet());
            task.setTags(tags);
        }

        return convertToDTO(taskRepository.save(task));
    }

    public TaskDTO updateTask(Long id, TaskDTO taskDTO) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        task.setTitle(taskDTO.getTitle());
        task.setDescription(taskDTO.getDescription());

        if (taskDTO.getCategoryId() != null) {
            Category category = categoryRepository.findById(taskDTO.getCategoryId())
                    .orElseThrow(() -> new RuntimeException("Category not found"));
            task.setCategory(category);
        } else {
            task.setCategory(null);
        }

        if (taskDTO.getTagIds() != null) {
            Set<Tag> tags = tagRepository.findAllById(taskDTO.getTagIds())
                    .stream()
                    .collect(Collectors.toSet());
            task.setTags(tags);
        } else {
            task.setTags(new HashSet<>());
        }

        return convertToDTO(taskRepository.save(task));
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    private TaskDTO convertToDTO(Task task) {
        return TaskDTO.builder()
                .id(task.getId())
                .title(task.getTitle())
                .description(task.getDescription())
                .categoryId(task.getCategory() != null ? task.getCategory().getId() : null)
                .category(task.getCategory() != null ? convertCategoryToDTO(task.getCategory()) : null)
                .tagIds(task.getTags().stream().map(Tag::getId).collect(Collectors.toSet()))
                .tags(task.getTags().stream().map(this::convertTagToDTO).collect(Collectors.toSet()))
                .createdAt(task.getCreatedAt())
                .updatedAt(task.getUpdatedAt())
                .build();
    }

    private CategoryDTO convertCategoryToDTO(Category category) {
        return CategoryDTO.builder()
                .id(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                .build();
    }

    private TagDTO convertTagToDTO(Tag tag) {
        return TagDTO.builder()
                .id(tag.getId())
                .name(tag.getName())
                .build();
    }
}