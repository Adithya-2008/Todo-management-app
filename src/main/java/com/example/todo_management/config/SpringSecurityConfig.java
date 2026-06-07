package com.example.todo_management.config;

import com.example.todo_management.security.JWTAuthenticationEntryPoint;
import com.example.todo_management.security.JwtAuthenticationFilter;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
@AllArgsConstructor
public class SpringSecurityConfig {

    private UserDetailsService userDetailsService;

    private JWTAuthenticationEntryPoint authenticationEntrypoint;

    private JwtAuthenticationFilter authenticationFilter;
    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authorize -> authorize
//                        .requestMatchers(HttpMethod.POST, "/api/**").hasRole("ADMIN")
//                        .requestMatchers(HttpMethod.DELETE, "/api/**").hasRole("ADMIN")
//                        .requestMatchers(HttpMethod.PUT, "/api/todo/updateTodoById/**").hasRole("ADMIN")
//                        .requestMatchers(HttpMethod.PATCH, "/api/todo/completeTodo/**", "/api/todo/incompleteTodo/**").hasAnyRole("USER", "ADMIN")
//                        .requestMatchers(HttpMethod.GET, "/api/**").hasAnyRole("USER", "ADMIN")
//
                                .requestMatchers("/api/user/**").permitAll()
                          .anyRequest().authenticated()
//                        //.anyRequest().permitAll()
                )
                .httpBasic(Customizer.withDefaults());

        http.exceptionHandling(exception->exception .authenticationEntryPoint(authenticationEntrypoint));
        http.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration){
        return authenticationConfiguration.getAuthenticationManager();
    }

}