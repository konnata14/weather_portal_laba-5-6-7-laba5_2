package com.example.weather_portal.service;

import com.example.weather_portal.model.Fuel;
import com.example.weather_portal.repository.FuelRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FuelService {

    private final FuelRepository repo;

    public FuelService(FuelRepository repo) {
        this.repo = repo;
    }

    public List<Fuel> getAllFuel() {
        return repo.findAll();
    }

    public void updateQuantity(String name, Double delta) {
        Fuel fuel = repo.findByName(name)
                .orElseThrow(() -> new RuntimeException("Топливо не найдено"));

        fuel.setQuantity(fuel.getQuantity() + delta);
        repo.save(fuel);
    }
}
