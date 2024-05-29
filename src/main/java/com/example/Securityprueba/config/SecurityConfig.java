package com.example.Securityprueba.config;


import com.example.Securityprueba.filter.JwtAuthenticationFilter;
import com.example.Securityprueba.service.userServices.UserDetailsServiceImp;
import org.springframework.boot.autoconfigure.security.reactive.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig  {

    private final JwtAuthenticationFilter jwtAuthenticattionFilter1;
    private final UserDetailsServiceImp userDetailsServiceImp;


    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticattionFilter1, UserDetailsServiceImp userDetailsServiceImp) {
        this.jwtAuthenticattionFilter1 = jwtAuthenticattionFilter1;
        this.userDetailsServiceImp = userDetailsServiceImp;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(req -> req
                        .requestMatchers(String.valueOf(PathRequest.toStaticResources()
                                .atCommonLocations())).permitAll()

                        .requestMatchers( "/api/v2/login/students" ,"/api/v2/login/admin",  "/votes/representatives"
                                ,"/api/v2/register/admin","authenticate/student" ,"api/v2/login/jury").permitAll()

                        //STUDENTS//

                        .requestMatchers("/api/v1/personero/findAllByGrade",

                                "/api/v1/personero/findByFullName/**",
                                "/api/v1/personero/findById/{id}","/api/v1/representative/findAllGrade"
                                ,"/api/v1/personero/findAllWithId",
                                "/api/v1/comptroller/list/comptrollerWithId",
                                "/vote" ).hasAuthority("STUDENT")
                        //ADMIN//
                        .requestMatchers( "save/comptroller"
                                ,"/count", "/votes/personero", "/votes/comptrollers",
                                "/api/v1/students1/findByName/{name}",
                                "api/v2/register/jury",
                              "/api/v1/students1/students1/findAll",
                                "list/comptroller", "findBy/{id}",
                                "/api/v1/students/register/students1","votes/**",
                                "/api/v1/students1/students/findByGrade/{grade}",
                                "/api/v1/representative/save",
                                "/api/v1/representative/**","api/v1/personero/save",
                                "/api/v1/personero/delete/**"
                                ,"/api/v1/students1/FindByIdentification/{identification}",
                                "/api/v1/representative/**"
                                ,"api/v1/personero/save","/api/v1/students1/findAll",
                                "/api/v1/personero/delete/**"
                                ,"/api/v1/students1//students/findByGradeOf11and10"
                                ,"api/v1/students1/students/findByGradeOf11"
                                ,"/api/v1/personero/upload/personero/{identification}"
                                ,"/deleteByIdentification/{identification}"
                                ,"/api/v2/findAll/jury"
                                , "/votes/representatives"
                        ,"api/v1/comptroller/upload/comptroller/{identification}"
                                ,"api/v1/representative/upload/representative/{identification}"
                        ).hasAuthority("ADMIN")
                        //JURY
                        .requestMatchers("/api/v1/students1/findAll",

                                "/api/v1/students1/FindByIdentification/{identification}"
                        ).hasAuthority("JURY")

                        .anyRequest().authenticated())
                .userDetailsService(userDetailsServiceImp)

                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                .addFilterBefore(jwtAuthenticattionFilter1, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }



}