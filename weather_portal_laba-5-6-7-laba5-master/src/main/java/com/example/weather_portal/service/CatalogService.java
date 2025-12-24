package com.example.weather_portal.service;

import com.example.weather_portal.model.CatalogItem;
import com.example.weather_portal.repository.CatalogItemRepository;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import java.util.List;

@Service
public class CatalogService {

    private final CatalogItemRepository repository;

    public CatalogService(CatalogItemRepository repository) {
        this.repository = repository;
    }

    public List<CatalogItem> findAll() {
        return repository.findAll();
    }

    // создаём пару тестовых товаров, если таблица пустая
    @PostConstruct
    public void initTestCatalog() {
        if (repository.count() > 0) {
            return;
        }

        CatalogItem item1 = new CatalogItem();
        item1.setName("Бензин АИ‑95");
        item1.setDescription("Высококачественный бензин АИ‑95.");

        CatalogItem item2 = new CatalogItem();
        item2.setName("Дизельное топливо");
        item2.setDescription("Зимнее дизельное топливо.");

        repository.save(item1);
        repository.save(item2);
    }
}
