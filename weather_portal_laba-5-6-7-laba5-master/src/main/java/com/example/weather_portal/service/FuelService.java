package com.example.weather_portal.service;

import com.example.weather_portal.model.Fuel;
import com.example.weather_portal.repository.FuelRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FuelService {

    private final FuelRepository fuelRepo;

    public FuelService(FuelRepository fuelRepo) {
        this.fuelRepo = fuelRepo;
    }

    // Получить все топлива
    public List<Fuel> findAll() {
        return fuelRepo.findAll();
    }

    // Найти топливо по ID
    public Fuel findById(Long id) {
        return fuelRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Fuel not found"));
    }

    // Сохранить или обновить топливо
    public Fuel save(Fuel fuel) {
        return fuelRepo.save(fuel);
    }

    // Удалить топливо
    public void deleteById(Long id) {
        fuelRepo.deleteById(id);
    }
}
