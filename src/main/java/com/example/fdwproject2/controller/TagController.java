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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String createTag(@RequestParam String name, RedirectAttributes redirectAttributes) {
        TagDTO tagDTO = TagDTO.builder()
                .name(name)
                .build();
        tagService.createTag(tagDTO);
        redirectAttributes.addFlashAttribute("successMessage", "Tag created successfully!");
        return "redirect:/tags";
    }

    @PostMapping("/{id}")
    public String updateTag(@PathVariable Long id, @RequestParam String name, RedirectAttributes redirectAttributes) {
        TagDTO tagDTO = TagDTO.builder()
                .id(id)
                .name(name)
                .build();
        tagService.updateTag(id, tagDTO);
        redirectAttributes.addFlashAttribute("successMessage", "Tag updated successfully!");
        return "redirect:/tags";
    }

    @PostMapping("/{id}/delete")
    public String deleteTag(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        tagService.deleteTag(id);
        redirectAttributes.addFlashAttribute("successMessage", "Tag deleted successfully!");
        return "redirect:/tags";
    }
}