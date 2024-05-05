package com.example.Securityprueba.controllers.AuntheticationControllers;

import com.example.Securityprueba.Dto.StudentsDTO.LoginStudentDto;
import com.example.Securityprueba.entities.SecurityModels.AuthenticationResponse;
import com.example.Securityprueba.entities.UserModels.Administrators;
import com.example.Securityprueba.entities.UserModels.Jury;
import com.example.Securityprueba.service.userServices.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;

@CrossOrigin(origins ="http://localhost:5173/")
@RequestMapping(value = "/api/v2")
@RestController
public class LoginControllers {
    @Autowired
    private   AuthenticationService authenticationService;
    @PostMapping("/login/admin")
    public ResponseEntity<?> loginAdmin(@RequestBody Administrators request) throws AuthenticationException {
        AuthenticationResponse response = authenticationService.authenticateAllLessStudents(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping(value = "/login/jury")

    public ResponseEntity<?> loginJury(@RequestBody Jury request) throws AuthenticationException {
        AuthenticationResponse response = authenticationService.authenticateAllLessStudents(request);
        return ResponseEntity.ok(response);
    }

}
