package com.example.weather_portal.controller;

import com.example.weather_portal.repository.NewsRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NewsController {

    private final NewsRepository newsRepository;

    public NewsController(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    @GetMapping("/news")
    public String newsList(Model model) {
        model.addAttribute("newsList", newsRepository.findAll());
        return "news-list";
    }
}
