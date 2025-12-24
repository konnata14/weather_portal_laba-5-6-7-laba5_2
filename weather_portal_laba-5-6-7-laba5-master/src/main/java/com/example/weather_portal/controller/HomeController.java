package com.example.weather_portal.controller;

import com.example.weather_portal.service.FuelService;
import com.example.weather_portal.service.NewsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;

@Controller
public class HomeController {

    private final FuelService fuelService;
    private final NewsService newsService;

    public HomeController(FuelService fuelService, NewsService newsService) {
        this.fuelService = fuelService;
        this.newsService = newsService;
    }

    @GetMapping("/")
    public String index(Model model) {
        // Каталог = все записи топлива
        model.addAttribute("catalogFuels", fuelService.findAllForCatalog());
        // Новости
        model.addAttribute("newsItems", newsService.findLatest());
        // Время сервера
        model.addAttribute("serverTime", LocalDateTime.now());
        return "index"; // templates/index.html
    }
}
