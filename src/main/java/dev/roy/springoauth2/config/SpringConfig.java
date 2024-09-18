package dev.roy.springoauth2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(registry -> {
                        registry.requestMatchers("/", "/login").permitAll();
                        registry.anyRequest().authenticated();
                })
                .oauth2Login(oauth2Login -> {
                    oauth2Login.loginPage("/login");
                    oauth2Login.successHandler((request, response, authentication) -> {
                        response.sendRedirect("/profile");
                    });
                })
                .build();
    }
}
