package com.example.weather_portal.controller;

import com.example.weather_portal.service.FuelService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FuelController {

    private final FuelService fuelService;

    public FuelController(FuelService fuelService) {
        this.fuelService = fuelService;
    }

    @GetMapping("/fuel")
    public String fuelPage(Model model) {
        model.addAttribute("fuelList", fuelService.getAllFuel());
        return "fuel";
    }
}
