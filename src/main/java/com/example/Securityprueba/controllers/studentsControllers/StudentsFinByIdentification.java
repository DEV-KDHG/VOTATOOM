package com.example.Securityprueba.controllers.studentsControllers;

import com.example.Securityprueba.Dto.StudentsDTO.StudentsDto;
import com.example.Securityprueba.entities.UserModels.Students;
import com.example.Securityprueba.repositories.UserRepositories.StudentsRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
@RestController
@RequestMapping(value = "/api/v1/students1")
public class StudentsFinByIdentification {
    private final StudentsRepository studentRepository;

    public StudentsFinByIdentification(StudentsRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    @GetMapping("/FindByIdentification/{identification}")
    public ResponseEntity<?> findByIdentification(@PathVariable Long identification) {
        Optional<Students> studentOptional = studentRepository.findStudentByIdentification(identification);

        if (studentOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Students student = studentOptional.get();
        StudentsDto studentDTO = mapToDTO(student);

        return ResponseEntity.ok(studentDTO);
    }

    private StudentsDto mapToDTO(Students student) {
        StudentsDto studentDTO = new StudentsDto();
        studentDTO.setName(student.getName());
        studentDTO.setLastName(student.getLastName());
        studentDTO.setGrade(String.valueOf(student.getGrade()));
        studentDTO.setIdentification(student.getIdentification());
        studentDTO.setCode(student.getCode());
        studentDTO.setStateVotation(student.getStateVotation());
        return studentDTO;
    }
}
