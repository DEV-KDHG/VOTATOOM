package com.example.Securityprueba.controllers.studentsControllers;

import com.example.Securityprueba.Dto.StudentsDTO.StudentDtoStateVotation;
import com.example.Securityprueba.Dto.StudentsDTO.StudentsDto;
import com.example.Securityprueba.entities.UserModels.Students;
import com.example.Securityprueba.entities.UserModels.Users;
import com.example.Securityprueba.repositories.UserRepositories.StudentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api/v1/students1")

public class StudentFindAll {
    @Autowired
    private StudentsRepository studentsRepository;


    @GetMapping("/api/v1/students1")
    public ResponseEntity<?> responseEntity() {
        List<Users> users = studentsRepository.findAll(); // Obtener usuarios

        if (users.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        List<StudentDtoStateVotation> studentDTOs = mapListToDTO(users);

        return ResponseEntity.ok(studentDTOs);
    }

    private List<StudentDtoStateVotation> mapListToDTO(List<Users> users) {
        List<StudentDtoStateVotation> studentDTOs = new ArrayList<>();
        for (Users user : users) {
            if (user instanceof Students) { // Verificar si es una instancia de Students
                Students student = (Students) user; // Convertir a Students
                StudentDtoStateVotation studentDTO = new StudentDtoStateVotation();
                studentDTO.setName(student.getName());
                studentDTO.setLastName(student.getLastName());
                studentDTO.setGrade((student.getGrade()));
                studentDTO.setIdentification(student.getIdentification());
                studentDTO.setCode(student.getCode());
                studentDTO.setStateVotation(student.getStateVotation());
                // Añade más asignaciones según sea necesario
                studentDTOs.add(studentDTO);
            }
        }
        return studentDTOs;
    }
}