package com.example.Securityprueba.controllers.studentsControllers;

import com.example.Securityprueba.entities.UserModels.Students;
import com.example.Securityprueba.entities.UserModels.Users;
import com.example.Securityprueba.entities.candidatesModels.Comptroller;
import com.example.Securityprueba.repositories.UserRepositories.StudentsRepository;
import com.example.Securityprueba.service.userServices.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(value = "/api/v1/students1")
public class StudentsDelete {
    private final StudentsRepository studentRepository;
private final AuthenticationService authenticationService;
    public StudentsDelete(StudentsRepository studentRepository, AuthenticationService authenticationService) {
        this.studentRepository = studentRepository;
        this.authenticationService = authenticationService;
    }
    @DeleteMapping("/DeleteById/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        Optional<Users> findById = studentRepository.findById(id);

        if (!findById.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        // Obtenemos el estudiante desde el Optional y pasamos el objeto estudiante al método de eliminación
        authenticationService.deleteStudentById(findById.get().getId());

        // Devolvemos una respuesta 200 (OK) con un mensaje indicando que se eliminó correctamente
        return ResponseEntity.ok("Se eliminó el estudiante con ID: " + id);
    }
}
