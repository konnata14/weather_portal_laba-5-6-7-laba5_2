package com.example.weather_portal.controller;

import com.example.weather_portal.model.Fuel;
import com.example.weather_portal.repository.FuelRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/fuel")
public class AdminFuelController {

    private final FuelRepository fuelRepository;

    public AdminFuelController(FuelRepository fuelRepository) {
        this.fuelRepository = fuelRepository;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("fuelList", fuelRepository.findAll());
        return "fuel-admin-list"; // шаблон должен быть в src/main/resources/templates/fuel-admin-list.html
    }


    @GetMapping("/new")
    public String newFuel(Model model) {
        model.addAttribute("fuel", new Fuel());
        return "fuel-form";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("fuel",
                fuelRepository.findById(id).orElseThrow()
        );
        return "fuel-form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Fuel fuel) {
        Fuel existingFuel;

        if (fuel.getId() != null) {
            existingFuel = fuelRepository.findById(fuel.getId()).orElseThrow();
            existingFuel.setName(fuel.getName());
            existingFuel.setPrice(fuel.getPrice());
            existingFuel.setQuantity(fuel.getQuantity());
            // Не трогаем createdBy
            fuelRepository.save(existingFuel);
        } else {
            // создание нового топлива
            fuelRepository.save(fuel);
        }

        // 🔹 Перенаправление на страницу списка
        return "redirect:/admin/fuel";
    }



    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        fuelRepository.deleteById(id);
        return "redirect:/admin/fuel";
    }
}
