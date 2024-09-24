package com.sagar.springSecurityExample;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // custom authentication configuration
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        // builder pattern
        http.csrf(customizer -> customizer.disable()) // disable the csrf token
                .authorizeHttpRequests(request -> request.anyRequest().authenticated()) // route must be authenticated
                //http.formLogin(Customizer.withDefaults()); // enable form login
                .httpBasic(Customizer.withDefaults()) // for REST Api
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)); // session id everytime (browser)

        return http.build(); // this build return the filter chain
    }
}
