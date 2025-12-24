package com.example.weather_portal.controller;

import com.example.weather_portal.repository.FuelRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FuelController {

    private final FuelRepository fuelRepository;

    public FuelController(FuelRepository fuelRepository) {
        this.fuelRepository = fuelRepository;
    }

    @GetMapping("/fuel")
    public String fuelList(Model model) {
        model.addAttribute("fuelList", fuelRepository.findAll());
        return "fuel-list";
    }
}
