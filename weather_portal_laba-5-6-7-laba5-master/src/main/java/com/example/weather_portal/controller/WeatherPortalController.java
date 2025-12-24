package com.example.weather_portal.controller;

import org.springframework.stereotype.Controller;

@Controller
public class WeatherPortalController {

    // Этот контроллер больше НЕ обрабатывает "/".
    // Раньше здесь был метод с @GetMapping("/"), который конфликтовал с HomeController.
    //
    // Если позже понадобится другая страница, можно добавить, например:
    //
    // @GetMapping("/about")
    // public String about() {
    //     return "about";
    // }
}
