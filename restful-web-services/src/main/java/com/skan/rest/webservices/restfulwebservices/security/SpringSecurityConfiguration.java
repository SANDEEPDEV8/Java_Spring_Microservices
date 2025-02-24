package com.skan.rest.webservices.restfulwebservices.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SpringSecurityConfiguration {


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

//        1) all the requests are authenticated
        http.authorizeHttpRequests(
                auth-> auth.anyRequest().authenticated()
        );
//        2) if the request not authenticated, a web page is shown
            http.httpBasic(withDefaults()); // this should alert box in browser to enter username password instead of login page

//        3) CSRF check that impact POST and PUT request
        http.csrf(csrf -> csrf.disable());

        return http.build();
    }
}
