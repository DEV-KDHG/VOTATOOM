package com.example.Securityprueba.controllers.AuntheticationControllers;

import com.example.Securityprueba.entities.SecurityModels.AuthenticationResponse;
import com.example.Securityprueba.entities.UserModels.Administrators;
import com.example.Securityprueba.entities.UserModels.Jury;
import com.example.Securityprueba.entities.UserModels.Students;
import com.example.Securityprueba.service.userServices.AuthenticationRequest;
import com.example.Securityprueba.service.userServices.AuthenticationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.AuthenticationException;

@RestController

public class AuthenticationController {

    private final AuthenticationService authenticationService;


    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }






    @PostMapping("/register/admin")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody Administrators request) {
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
    public ResponseEntity<?> loginAdmin(@RequestBody Administrators request) throws AuthenticationException {
        AuthenticationResponse response = authenticationService.authenticate(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping(value = "/login/jury")

    public ResponseEntity<?> loginJury(@RequestBody Jury request) throws AuthenticationException {
        AuthenticationResponse response = authenticationService.authenticate(request);
        return ResponseEntity.ok(response);
    }
    @PostMapping("/authenticate/student")
    public ResponseEntity<?> authenticateStudent(@RequestBody AuthenticationRequest request) {
        try {
            // Autenticar al estudiante y obtener el token de autenticación
            AuthenticationResponse response = authenticationService.authenticateStudent(request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            // Manejar cualquier excepción y devolver una respuesta de error
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Error de autenticación: " + e.getMessage());
        }
    }

}

