package com.example.fdwproject2.controller;

import com.example.fdwproject2.DTO.UserLoginDto;
import com.example.fdwproject2.DTO.UserRegistrationDto;
import com.example.fdwproject2.repository.LoginAttemptRepository;
import com.example.fdwproject2.repository.UserRepository;
import com.example.fdwproject2.service.AuthenticationService;
import com.example.fdwproject2.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.time.LocalDateTime;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService authService;
    private final UserService userService;
    private final LoginAttemptRepository loginAttemptRepository;
    private final UserRepository userRepository;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new UserRegistrationDto());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") UserRegistrationDto userDto,
                               HttpServletRequest request) {
        try {
            authService.register(userDto, request.getRemoteAddr());
            return "redirect:/login?success";
        } catch (Exception e) {
            return "redirect:/register?error";
        }
    }

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("user", new UserLoginDto());
        return "login";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("loginAttempts", loginAttemptRepository.findAll());
        model.addAttribute("users", userRepository.findAll());
        return "dashboard";
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping("/access-denied")
    public String accessDenied() {
        return "access-denied";
    }
}