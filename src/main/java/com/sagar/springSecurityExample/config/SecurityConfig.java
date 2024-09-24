package com.sagar.springSecurityExample.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UserDetailsService userDetailsService;

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

//    @Bean
//    public UserDetailsService userDetailsService() {
//        UserDetails user1 = User
//                .withDefaultPasswordEncoder()
//                .username("sagar")
//                .password("sagar1")
//                .roles("USER")
//                .build();
//
//        UserDetails user2 = User
//                .withDefaultPasswordEncoder()
//                .username("sagark")
//                .password("sagar123")
//                .roles("ADMIN")
//                .build();
//
//        UserDetails user3 = User
//                .withDefaultPasswordEncoder()
//                .username("sagar")
//                .password("sagar")
//                .roles("ADMIN, USER")
//                .build();
//
//        return new InMemoryUserDetailsManager(user1, user2, user3);
//    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
        authProvider.setUserDetailsService(userDetailsService);
        return authProvider;
    }
}
