package com.telusko.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf(AbstractHttpConfigurer::disable).
                authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/").permitAll()
                                .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults());


        return http.build();
    }


    @Bean
    public UserDetailsService userDetailsService() {

        UserDetails user = User
                .withDefaultPasswordEncoder()
                .username("navin").password("n@123")
                .roles("USER")
                .build();

        UserDetails admin = User
                .withDefaultPasswordEncoder()
                .username("admin").password("admin@789")
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(user,admin);
    }

}
