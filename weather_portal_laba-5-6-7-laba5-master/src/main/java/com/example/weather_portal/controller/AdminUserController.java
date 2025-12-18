package com.example.weather_portal.controller;

import com.example.weather_portal.model.User;
import com.example.weather_portal.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/users")
public class AdminUserController {

    private final UserRepository userRepository;

    public AdminUserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // 1️⃣ Список пользователей
    @GetMapping
    public String listUsers(Model model) {
        List<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "users-list"; // thymeleaf-шаблон для списка
    }

    // 2️⃣ Форма добавления нового пользователя
    @GetMapping("/new")
    public String newUserForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("roles", List.of("USER", "MODERATOR", "ADMIN"));
        return "user-form";
    }

    // 3️⃣ Форма редактирования существующего пользователя
    @GetMapping("/edit/{id}")
    public String editUserForm(@PathVariable Long id, Model model) {
        User user = userRepository.findById(id).orElseThrow();
        model.addAttribute("user", user);
        model.addAttribute("roles", List.of("USER", "MODERATOR", "ADMIN"));
        return "user-form";
    }

    // 4️⃣ Сохранение нового/редактированного пользователя
    @PostMapping("/save")
    public String saveUser(@ModelAttribute User user) {
        // Если пароль пустой, оставляем старый
        if (user.getPassword() == null || user.getPassword().isBlank()) {
            User oldUser = userRepository.findById(user.getId()).orElseThrow();
            user.setPassword(oldUser.getPassword());
        } else {
            // Иначе можно зашифровать пароль
            // user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        userRepository.save(user);
        return "redirect:/admin/users";
    }

    // 5️⃣ Удаление пользователя
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userRepository.deleteById(id);
        return "redirect:/admin/users";
    }
}
