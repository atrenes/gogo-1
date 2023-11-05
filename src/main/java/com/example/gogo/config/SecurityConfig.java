package com.example.gogo.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Map;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final Map<String, String[]> patternMap = Map.of(
            "admin", new String[]{
                    "*/register",
                    "*/stand/create"
            },
            "default", new String[]{
                    "*/leaderboard",
                    "*/inventory",
                    "*/fights/*",
                    "*/leaderboard"
            },
            "vip", new String[]{
                    "*/leaderboard",
                    "*/inventory",
                    "*/fights/*"
            }
    );

    private final Filter filter;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeRequests()
                .requestMatchers(patternMap.get("admin")).hasRole("admin")
                .requestMatchers(patternMap.get("default")).hasRole("default")
                .requestMatchers(patternMap.get("vip")).hasRole("vip")
                .requestMatchers("/login").permitAll()
                .and()
                .csrf(AbstractHttpConfigurer::disable)
                .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
