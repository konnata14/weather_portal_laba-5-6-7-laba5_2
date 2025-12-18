package com.example.weather_portal.initializer;

import com.example.weather_portal.model.Role;
import com.example.weather_portal.model.User;
import com.example.weather_portal.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.example.weather_portal.repository.FuelRepository;
import com.example.weather_portal.model.Fuel;



@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner init(UserRepository userRepository, PasswordEncoder encoder) {
        return args -> {

            // Создаём админа
            if (userRepository.findByUsername("admin").isEmpty()) {
                User admin = new User();
                admin.setUsername("admin");
                admin.setPassword(encoder.encode("adminpass"));
                admin.setRole(Role.ROLE_ADMIN);
                userRepository.save(admin);
            }

            // Создаём модератора
            if (userRepository.findByUsername("mod").isEmpty()) {
                User mod = new User();
                mod.setUsername("mod");
                mod.setPassword(encoder.encode("modpass"));
                mod.setRole(Role.ROLE_MODERATOR);
                userRepository.save(mod);
            }

            // Создаём обычного пользователя
            if (userRepository.findByUsername("user").isEmpty()) {
                User user = new User();
                user.setUsername("user");
                user.setPassword(encoder.encode("userpass"));
                user.setRole(Role.ROLE_USER);
                userRepository.save(user);
            }
        };
    }
    @Bean
    CommandLineRunner initFuel(FuelRepository fuelRepo) {
        return args -> {

            if (fuelRepo.count() == 0) {

                Fuel f92 = new Fuel();
                f92.setName("АИ-92");
                f92.setPrice(50.0);
                f92.setQuantity(5000.0);

                Fuel f95 = new Fuel();
                f95.setName("АИ-95");
                f95.setPrice(55.0);
                f95.setQuantity(4000.0);

                Fuel gas = new Fuel();
                gas.setName("Газ");
                gas.setPrice(30.0);
                gas.setQuantity(3000.0);

                fuelRepo.save(f92);
                fuelRepo.save(f95);
                fuelRepo.save(gas);
            }
        };
    }


}
