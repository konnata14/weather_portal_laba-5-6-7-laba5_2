package com.example.weather_portal.repository;

import com.example.weather_portal.model.Fuel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FuelRepository extends JpaRepository<Fuel, Long> {

    // предполагаем, что в Fuel есть поле ownerUsername (String)
    List<Fuel> findByOwnerUsername(String ownerUsername);
}
