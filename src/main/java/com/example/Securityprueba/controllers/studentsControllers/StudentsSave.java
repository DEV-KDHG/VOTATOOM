package com.example.Securityprueba.controllers.studentsControllers;

import com.example.Securityprueba.Dto.StudentsDTO.StudentsDto;
import com.example.Securityprueba.entities.SecurityModels.AuthenticationResponse;
import com.example.Securityprueba.entities.UserModels.Students;
import com.example.Securityprueba.service.userServices.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins ="http://localhost:5173/")
@RequestMapping(value = "/api/v1/students")
public class StudentsSave {

    @Autowired
    private  AuthenticationService authenticationService;
    @PostMapping("/register/students1")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody Students request) {


        AuthenticationResponse response = authenticationService.registerStudent( request);
        return ResponseEntity.ok(response);
    }





}
