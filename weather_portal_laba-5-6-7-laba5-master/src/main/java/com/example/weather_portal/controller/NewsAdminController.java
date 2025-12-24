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

    // список новостей с кнопками
    @GetMapping
    public String adminList(Model model) {
        model.addAttribute("newsList", newsRepository.findAll());
        return "news-admin-list";
    }

    // форма добавления новой
    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("news", new News());
        return "news-form";
    }

    // форма редактирования
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("news", newsRepository.findById(id).orElseThrow());
        return "news-form";
    }

    // сохранение
    @PostMapping("/save")
    public String save(@ModelAttribute News news) {
        newsRepository.save(news);
        return "redirect:/moderator/news";
    }

    // удаление
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        newsRepository.deleteById(id);
        return "redirect:/moderator/news";
    }
}
