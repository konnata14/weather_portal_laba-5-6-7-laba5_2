package com.example.weather_portal.repository;   // ← 1. пакет

import com.example.weather_portal.model.CatalogItem;          // ← 2. модель
import org.springframework.data.jpa.repository.JpaRepository; // ← 3. JPA
import org.springframework.stereotype.Repository;             // ← 4. @Repository

@Repository
public interface CatalogItemRepository extends JpaRepository<CatalogItem, Long> {
}
