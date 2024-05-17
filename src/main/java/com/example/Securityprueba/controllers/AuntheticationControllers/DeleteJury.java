package com.example.Securityprueba.controllers.AuntheticationControllers;

import com.example.Securityprueba.entities.UserModels.Students;
import com.example.Securityprueba.entities.UserModels.Users;
import com.example.Securityprueba.repositories.UserRepositories.JuryRepository;
import com.example.Securityprueba.repositories.UserRepositories.StudentsRepository;
import com.example.Securityprueba.service.userServices.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping(value = "/api/v1/jury")
public class DeleteJury {




        @Autowired
        private JuryRepository studentRepository;

        @Autowired
        private AuthenticationService authenticationService;

        @DeleteMapping("/deleteByIdentification/{identification}")
        @Transactional
        public ResponseEntity<String> deleteByIdentification(@PathVariable Long identification) {
            try {
                Optional<Users> studentOptional = studentRepository.findUserByIdentification(identification);
                Optional<Students> optionalStudents = studentRepository.findStudentByIdentification(identification);
                if (optionalStudents.isPresent()) {
                    Students student = (Students) studentOptional.get();

                    // Ejemplo de acceso a propiedades del estudiante
                    System.out.println("Estudiante encontrado: " + student.getIdentification());
                    System.out.println("Estudiante encontrado: " + student.getName());

                    // Eliminar el estudiante
                    authenticationService.deleteStudentByIdentification(student.getIdentification());
                    studentRepository.deleteByIdentification(identification);
                    return ResponseEntity.ok("Se eliminó el estudiante con Identificación: " + identification);

                }


            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("Error al eliminar el estudiante: " + e.getMessage());

            }
            return ResponseEntity.ok("Bueno otra cosa que no se que fue");
        }}