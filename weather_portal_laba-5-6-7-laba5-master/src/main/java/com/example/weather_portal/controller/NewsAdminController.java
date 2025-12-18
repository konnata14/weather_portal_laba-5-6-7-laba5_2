package com.example.weather_portal.controller;

import com.example.weather_portal.model.News;
import com.example.weather_portal.repository.NewsRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/moderator/news")
public class NewsAdminController {

    private final NewsRepository newsRepository;

    public NewsAdminController(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    // Список новостей с кнопками Редактировать/Удалить/Добавить
    @GetMapping
    public String adminList(Model model) {
        model.addAttribute("newsList", newsRepository.findAll());
        return "news-admin-list";
    }

    // Форма добавления новой новости
    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("news", new News());
        return "news-form";
    }

    // Форма редактирования существующей новости
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("news", newsRepository.findById(id).orElseThrow());
        return "news-form";
    }

    // Сохранение новости (новая или редактируемая)
    @PostMapping("/save")
    public String save(@ModelAttribute News news) {
        newsRepository.save(news);
        return "redirect:/moderator/news";
    }

    // Удаление новости
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        newsRepository.deleteById(id);
        return "redirect:/moderator/news";
    }
}
