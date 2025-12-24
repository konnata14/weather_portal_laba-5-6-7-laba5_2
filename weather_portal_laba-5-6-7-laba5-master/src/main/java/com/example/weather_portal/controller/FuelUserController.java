package com.example.weather_portal.controller;

import com.example.weather_portal.model.Fuel;
import com.example.weather_portal.service.FuelService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/user/fuel")
public class FuelUserController {

    private final FuelService fuelService;

    public FuelUserController(FuelService fuelService) {
        this.fuelService = fuelService;
    }

    // Список "Мои товары" — сейчас показываем все, как в каталоге
    @GetMapping
    public String userFuel(Model model, Principal principal) {
        // если нужно именно "как каталог", берём все записи
        List<Fuel> fuels = fuelService.findAllForCatalog();
        model.addAttribute("fuels", fuels);
        return "user_fuel_list"; // шаблон user_fuel_list.html
    }

    // Форма добавления нового товара
    @GetMapping("/new")
    public String newFuel(Model model) {
        model.addAttribute("fuel", new Fuel());
        return "user_fuel_form";
    }

    // Форма редактирования
    @GetMapping("/edit/{id}")
    public String editFuel(@PathVariable Long id, Model model, Principal principal) {
        Fuel fuel = fuelService.findForUser(id, principal.getName());
        model.addAttribute("fuel", fuel);
        return "user_fuel_form";
    }

    // Сохранение (создание / обновление)
    @PostMapping("/save")
    public String saveFuel(@ModelAttribute Fuel fuel, Principal principal) {
        fuelService.saveForUser(fuel, principal.getName());
        return "redirect:/user/fuel";
    }

    // Удаление
    @GetMapping("/delete/{id}")
    public String deleteFuel(@PathVariable Long id, Principal principal) {
        fuelService.deleteForUser(id, principal.getName());
        return "redirect:/user/fuel";
    }
}
