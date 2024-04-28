package com.example.Securityprueba.controllers.studentsControllers;

import com.example.Securityprueba.Dto.StudentsDTO.StudentDtoStateVotation;
import com.example.Securityprueba.Dto.StudentsDTO.StudentsDto;
import com.example.Securityprueba.entities.UserModels.Students;
import com.example.Securityprueba.repositories.UserRepositories.StudentsRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "*")
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
        StudentDtoStateVotation studentDTO = mapToDTO(student);

        return ResponseEntity.ok(studentDTO);
    }

    private StudentDtoStateVotation mapToDTO(Students student) {
        StudentDtoStateVotation studentDTO = new StudentDtoStateVotation();
        studentDTO.setName(student.getName());
        studentDTO.setLastName(student.getLastName());
        studentDTO.setGrade((student.getGrade()));
        studentDTO.setIdentification(student.getIdentification());
        studentDTO.setCode(student.getCode());
        studentDTO.setStateVotation(student.getStateVotation());
        return studentDTO;
    }
}
