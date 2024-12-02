package com.example.fdwproject2.controller;

import com.example.fdwproject2.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/changeRole")
    public String changeUserRole(@RequestParam Long userId,
                                 @RequestParam String newRole) {
        userService.changeUserRole(userId, newRole);
        return "redirect:/dashboard";
    }
}