package com.example.weather_portal.config;

import com.example.weather_portal.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    private final CustomUserDetailsService userDetailsService;

    public SecurityConfig(CustomUserDetailsService uds) {
        this.userDetailsService = uds;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .authenticationProvider(authProvider())

                .authorizeHttpRequests(auth -> auth
                        // публичные ресурсы
                        .requestMatchers(
                                "/css/**",
                                "/js/**",
                                "/images/**",
                                "/register",
                                "/login",
                                "/fuel",
                                "/fuel/**",
                                "/news",
                                "/h2-console/**",
                                "/uploads/**",
                                "/photo/**"
                        ).permitAll()

                        // полный доступ для админа
                        .requestMatchers("/admin/**").hasRole("ADMIN")

                        // модераторские страницы новостей
                        .requestMatchers("/moderator/news/**").hasAnyRole("MODERATOR", "ADMIN")

                        // доступ пользователей к добавлению топлива
                        .requestMatchers("/user/fuel/**").hasAnyRole("USER", "MODERATOR", "ADMIN")

                        // всё остальное требует авторизации
                        .anyRequest().authenticated()
                )


                .formLogin(form -> form
                        .loginPage("/login")
                        .permitAll()
                        .defaultSuccessUrl("/profile", true)
                )

                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                        .permitAll()
                )

                .csrf(csrf -> csrf.disable())

                .headers(headers -> headers.frameOptions(frame -> frame.sameOrigin()));

        return http.build();
    }
}
