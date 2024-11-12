package com.example.fdwproject2.service;
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
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
public class TagService {
    private final TagRepository tagRepository;

    public List<TagDTO> getAllTags() {
        return tagRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public TagDTO getTagById(Long id) {
        Tag tag = tagRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tag not found"));
        return convertToDTO(tag);
    }

    public TagDTO createTag(TagDTO tagDTO) {
        Tag tag = new Tag();
        tag.setName(tagDTO.getName());
        return convertToDTO(tagRepository.save(tag));
    }

    public TagDTO updateTag(Long id, TagDTO tagDTO) {
        Tag tag = tagRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tag not found"));
        tag.setName(tagDTO.getName());
        return convertToDTO(tagRepository.save(tag));
    }

    public void deleteTag(Long id) {
        tagRepository.deleteById(id);
    }

    private TagDTO convertToDTO(Tag tag) {
        Set<TaskDTO> taskDTOs = tag.getTasks().stream()
                .map(task -> TaskDTO.builder()
                        .id(task.getId())
                        .title(task.getTitle())
                        .build())
                .collect(Collectors.toSet());

        return TagDTO.builder()
                .id(tag.getId())
                .name(tag.getName())
                .tasks(taskDTOs)
                .createdAt(tag.getCreatedAt())
                .updatedAt(tag.getUpdatedAt())
                .build();
    }
}
