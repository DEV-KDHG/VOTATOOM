package com.example.Securityprueba.controller;

import com.example.Securityprueba.entities.*;
import com.example.Securityprueba.service.AuthenticationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.AuthenticationException;

@RestController

public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }



    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody Students request) {

        request.setGrade(request.getGrade());
        // Suponiendo que el método register de authenticationService devuelve directamente la respuesta de autenticación
        AuthenticationResponse response = authenticationService.register( request);
        return ResponseEntity.ok(response);
    }



    @PostMapping("/register/admin")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody administrator request) {
        AuthenticationResponse response = authenticationService.registerAdmin(request);
        return ResponseEntity.ok(response);

    }

    @PostMapping("/register/jury")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody Jury request) {
        AuthenticationResponse response = authenticationService.registerJury( request);
        return ResponseEntity.ok(response);

    }

    @PostMapping("/login/students")
    public ResponseEntity<?> loginStudents(@RequestBody Students request) throws AuthenticationException {
        AuthenticationResponse response = authenticationService.authenticate(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login/admin")
    public ResponseEntity<?> loginAdmin(@RequestBody administrator request) throws AuthenticationException {
        AuthenticationResponse response = authenticationService.authenticate(request);
        return ResponseEntity.ok(response);
    }


}

