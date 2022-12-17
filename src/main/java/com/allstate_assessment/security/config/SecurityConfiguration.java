package com.allstate_assessment.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {


    @Bean
    public InMemoryUserDetailsManager userDetailsManager() {
        UserDetails admin = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("admin")
                .roles("admin")
                .build();
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("user")
                .password("user")
                .roles("user")
                .build();
        return new InMemoryUserDetailsManager(user, admin);
    }

    // Configuring the apis according the roles assigned
    @Bean
    public SecurityFilterChain configureApis(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/phonebook/addPerson").hasRole("admin");
                    auth.requestMatchers("/phonebook/updatePerson/**").hasRole("admin");
                    auth.requestMatchers("/phonebook/deletePerson/**").hasRole("admin");
                    auth.requestMatchers("/phonebook/**").hasAnyRole("user", "admin");
                    auth.requestMatchers("http://localhost:8010/h2-console").hasRole("admin");
                    auth.requestMatchers("/phonebook/getAllPersons").hasAnyRole("user", "admin");
                })
                .httpBasic(Customizer.withDefaults())
                .build();
    }




}
