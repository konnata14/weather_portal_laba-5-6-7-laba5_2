package com.example.weather_portal.repository;

import com.example.weather_portal.model.NewsItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsItemRepository extends JpaRepository<NewsItem, Long> {

    List<NewsItem> findTop10ByOrderByDateDesc();
}
