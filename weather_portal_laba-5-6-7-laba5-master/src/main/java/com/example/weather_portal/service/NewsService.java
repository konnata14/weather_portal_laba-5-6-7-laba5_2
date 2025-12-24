package com.example.weather_portal.service;

import com.example.weather_portal.model.News;
import com.example.weather_portal.repository.NewsRepository;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import java.util.List;

@Service
public class NewsService {

    private final NewsRepository newsRepository;

    public NewsService(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    public List<News> findAll() {
        return newsRepository.findAll();
    }

    public List<News> findLatest() {
        return newsRepository.findAll();
    }

    @PostConstruct
    public void createTestNews() {
        if (newsRepository.count() > 0) {
            return;
        }

        News n1 = new News();
        n1.setTitle("Добро пожаловать на портал");
        n1.setContent("Это тестовая новость №1.");

        News n2 = new News();
        n2.setTitle("Акции и скидки");
        n2.setContent("Это тестовая новость №2.");

        newsRepository.save(n1);
        newsRepository.save(n2);
    }
}
