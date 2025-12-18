package com.example.weather_portal.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "fuel")
public class Fuel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Название топлива (АИ-95, ДТ, Газ)
    @Column(nullable = false, unique = true)
    private String name;

    // Цена за литр
    @Column(nullable = false)
    private Double price;

    // Количество в литрах
    @Column(nullable = false)
    private Double quantity;

    private LocalDateTime updatedAt;

    @PrePersist
    @PreUpdate
    public void updateTime() {
        updatedAt = LocalDateTime.now();
    }
    @ManyToOne
    @JoinColumn(name = "created_by")
    private User createdBy;

}
