package com.example.Securityprueba.controllers.AuntheticationControllers;

import com.example.Securityprueba.entities.SecurityModels.AuthenticationResponse;
import com.example.Securityprueba.entities.UserModels.Administrators;
import com.example.Securityprueba.entities.UserModels.Jury;
import com.example.Securityprueba.service.userServices.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<AuthenticationResponse> register(@RequestBody Jury request) {
        AuthenticationResponse response = authenticationService.registerJury( request);
        return ResponseEntity.ok(response);

    }
}
