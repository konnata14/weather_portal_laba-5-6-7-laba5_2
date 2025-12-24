package com.example.weather_portal.controller;

import com.example.weather_portal.model.Role;
import com.example.weather_portal.model.User;
import com.example.weather_portal.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/users")
public class AdminUserController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AdminUserController(UserRepository userRepository,
                               PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // список пользователей
    @GetMapping
    public String listUsers(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "user-list";
    }

    // форма создания нового пользователя
    @GetMapping("/new")
    public String newUserForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("roles", Role.values());
        return "user-form";
    }

    // форма редактирования
    @GetMapping("/edit/{id}")
    public String editUserForm(@PathVariable Long id, Model model) {
        User user = userRepository.findById(id).orElseThrow();
        user.setPassword(""); // не показываем настоящий пароль
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        return "user-form";
    }

    // создание / редактирование
    @PostMapping("/save")
    public String saveUser(@ModelAttribute User formUser) {

        User user;

        if (formUser.getId() != null) {
            // редактирование
            user = userRepository.findById(formUser.getId()).orElseThrow();

            user.setUsername(formUser.getUsername());
            user.setRole(formUser.getRole());

            if (formUser.getPassword() != null && !formUser.getPassword().isBlank()) {
                user.setPassword(passwordEncoder.encode(formUser.getPassword()));
            }

        } else {
            // создание
            user = new User();
            user.setUsername(formUser.getUsername());
            user.setRole(formUser.getRole());

            if (formUser.getPassword() == null || formUser.getPassword().isBlank()) {
                throw new RuntimeException("Пароль обязателен");
            }

            user.setPassword(passwordEncoder.encode(formUser.getPassword()));
        }

        userRepository.save(user);
        return "redirect:/admin/users";
    }

    // удаление пользователя (запрет удаления себя)
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id, Authentication auth) {

        User target = userRepository.findById(id).orElseThrow();

        if (target.getUsername().equals(auth.getName())) {
            return "redirect:/admin/users?error=selfDelete";
        }

        userRepository.delete(target);
        return "redirect:/admin/users";
    }
}
