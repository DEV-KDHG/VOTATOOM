package com.example.Securityprueba.controllers.studentsControllers;

import com.example.Securityprueba.Dto.StudentsDTO.LoginStudentDto;
import com.example.Securityprueba.entities.SecurityModels.AuthenticationResponse;
import com.example.Securityprueba.entities.UserModels.Students;
import com.example.Securityprueba.service.userServices.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api/v1/students")
public class StudentsAuthentication {
    @Autowired
    private  AuthenticationService authenticationService;

    @PostMapping("/login/students")
    public ResponseEntity<?> loginStudents(@RequestBody LoginStudentDto request) throws AuthenticationException {
        AuthenticationResponse response = authenticationService.authenticateStudent(request);
        return ResponseEntity.ok(response);
    }
}
