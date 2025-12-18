package com.example.weather_portal.service;

import com.example.weather_portal.model.News;
import com.example.weather_portal.repository.NewsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsService {

    private final NewsRepository repo;

    public NewsService(NewsRepository repo) {
        this.repo = repo;
    }

    public List<News> getAll() {
        return repo.findAll();
    }

    public News getById(Long id) {
        return repo.findById(id).orElseThrow();
    }

    public void save(News news) {
        repo.save(news);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
