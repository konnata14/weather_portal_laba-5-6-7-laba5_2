package com.example.weather_portal.repository;

import com.example.weather_portal.model.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {
    // Дополнительные методы пока не нужны
}
