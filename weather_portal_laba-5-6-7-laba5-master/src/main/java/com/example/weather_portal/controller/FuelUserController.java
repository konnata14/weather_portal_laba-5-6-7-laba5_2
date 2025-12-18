package com.example.weather_portal.controller;

import com.example.weather_portal.model.Fuel;
import com.example.weather_portal.model.User;
import com.example.weather_portal.repository.FuelRepository;
import com.example.weather_portal.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user/fuel")
public class FuelUserController {

    private final FuelRepository fuelRepo;
    private final UserRepository userRepo;

    public FuelUserController(FuelRepository fuelRepo, UserRepository userRepo) {
        this.fuelRepo = fuelRepo;
        this.userRepo = userRepo;
    }

    // форма добавления
    @GetMapping("/new")
    public String newFuel(Model model) {
        model.addAttribute("fuel", new Fuel());
        return "fuel-form";
    }

    // сохранение
    @PostMapping("/save")
    public String saveFuel(@ModelAttribute Fuel fuel,
                           Authentication auth) {

        User user = userRepo.findByUsername(auth.getName())
                .orElseThrow();

        fuel.setCreatedBy(user);
        fuelRepo.save(fuel);

        return "redirect:/fuel";
    }
}
