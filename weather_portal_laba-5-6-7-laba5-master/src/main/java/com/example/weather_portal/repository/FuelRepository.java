package com.example.weather_portal.repository;

import com.example.weather_portal.model.Fuel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FuelRepository extends JpaRepository<Fuel, Long> {

    Optional<Fuel> findByName(String name);
}
