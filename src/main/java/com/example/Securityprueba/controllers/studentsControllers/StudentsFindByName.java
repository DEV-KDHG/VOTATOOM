package com.example.Securityprueba.controllers.studentsControllers;

import com.example.Securityprueba.Dto.StudentsDTO.StudentsDto;
import com.example.Securityprueba.entities.UserModels.Students;
import com.example.Securityprueba.entities.UserModels.Users;
import com.example.Securityprueba.repositories.UserRepositories.StudentsRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/students1")
public class StudentsFindByName {
    private final StudentsRepository studentRepository;

    public StudentsFindByName(StudentsRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping("/FindByName/{name}")
    public ResponseEntity<?> findByName(@PathVariable String name) {
        List<Users> users = studentRepository.findAllByName(name); // Obtener usuarios

        if (users.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        List<StudentsDto> studentDTOs = mapListToDTO(users);

        return ResponseEntity.ok(studentDTOs);
    }

    private List<StudentsDto> mapListToDTO(List<Users> users) {
        List<StudentsDto> studentDTOs = new ArrayList<>();
        for (Users user : users) {
            if (user instanceof Students) { // Verificar si es una instancia de Students
                Students student = (Students) user; // Convertir a Students
                StudentsDto studentDTO = new StudentsDto();
                studentDTO.setName(student.getName());
                studentDTO.setLastName(student.getLastName());
                studentDTO.setGrade(student.getGrade());
                studentDTO.setIdentification(student.getIdentification());
                // Añade más asignaciones según sea necesario
                studentDTOs.add(studentDTO);
            }
        }
        return studentDTOs;
    }
}
