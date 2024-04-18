package com.example.Securityprueba.controllers.AuntheticationControllers;

import com.example.Securityprueba.entities.SecurityModels.AuthenticationResponse;
import com.example.Securityprueba.entities.UserModels.Administrators;
import com.example.Securityprueba.entities.UserModels.Jury;
import com.example.Securityprueba.service.userServices.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterControllers {
    @Autowired
    private  AuthenticationService authenticationService;
    @PostMapping("/register/admin")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody Administrators request) {
        AuthenticationResponse response = authenticationService.registerAdmin(request);
        return ResponseEntity.ok(response);

    }
    @PostMapping("/register/jury")
    public ResponseEntity<AuthenticationResponse> registerJury(@RequestBody Jury request) {
        try {
            AuthenticationResponse response = authenticationService.registerJury(request);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new AuthenticationResponse(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new AuthenticationResponse("Error al registrar el jurado."));
        }
    }

}
