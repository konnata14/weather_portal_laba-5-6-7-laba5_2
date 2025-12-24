package com.example.weather_portal.model;   // ← 1. строка

import jakarta.persistence.*;              // ← JPA аннотации
import lombok.Data;                       // ← @Data

@Data
@Entity
@Table(name = "catalog_items")
public class CatalogItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private String category;
    private Double price;
}
