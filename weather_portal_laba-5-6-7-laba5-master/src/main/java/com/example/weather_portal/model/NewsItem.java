package com.example.weather_portal.model;   // ← 1. строка

import jakarta.persistence.*;              // ← JPA аннотации
import lombok.Data;                       // ← @Data
import java.time.LocalDateTime;           // ← LocalDateTime

@Data
@Entity
@Table(name = "news_items")
public class NewsItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    private String author;

    private LocalDateTime date = LocalDateTime.now();
}
