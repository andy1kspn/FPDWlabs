package com.example.fdwproject2.controller;

import com.example.fdwproject2.DTO.TagDTO;
import com.example.fdwproject2.service.TagService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/tags")
@RequiredArgsConstructor
public class TagController {
    private final TagService tagService;

    @GetMapping
    public String listTags(Model model) {
        model.addAttribute("tags", tagService.getAllTags());
        return "tags/list";
    }

    @PostMapping
    public String createTag(@RequestParam String name) {
        TagDTO tagDTO = TagDTO.builder()
                .name(name)
                .build();
        tagService.createTag(tagDTO);
        return "redirect:/tags";
    }

    @PostMapping("/{id}")
    public String updateTag(@PathVariable Long id, @RequestParam String name) {
        TagDTO tagDTO = TagDTO.builder()
                .id(id)
                .name(name)
                .build();
        tagService.updateTag(id, tagDTO);
        return "redirect:/tags";
    }

    @PostMapping("/{id}/delete")
    public String deleteTag(@PathVariable Long id) {
        tagService.deleteTag(id);
        return "redirect:/tags";
    }
}