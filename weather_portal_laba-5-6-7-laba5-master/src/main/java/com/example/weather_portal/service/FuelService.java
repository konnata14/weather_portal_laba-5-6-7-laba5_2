package com.example.weather_portal.service;

import com.example.weather_portal.model.Fuel;
import com.example.weather_portal.repository.FuelRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FuelService {

    private final FuelRepository fuelRepository;

    public FuelService(FuelRepository fuelRepository) {
        this.fuelRepository = fuelRepository;
    }

    // Товары конкретного пользователя (для "Моих товаров")
    public List<Fuel> findByUsername(String username) {
        return fuelRepository.findByOwnerUsername(username);
    }

    // Один товар по id
    public Fuel findForUser(Long fuelId, String username) {
        return fuelRepository.findById(fuelId)
                .orElseThrow(() -> new RuntimeException("Товар не найден: " + fuelId));
    }

    // Сохранить/обновить товар пользователя
    public void saveForUser(Fuel fuel, String username) {
        fuel.setOwnerUsername(username);
        fuelRepository.save(fuel);
    }

    // Удалить товар пользователя
    public void deleteForUser(Long fuelId, String username) {
        fuelRepository.deleteById(fuelId);
    }

    // ВСЕ товары для каталога (главная)
    public List<Fuel> findAllForCatalog() {
        return fuelRepository.findAll();
    }
}
