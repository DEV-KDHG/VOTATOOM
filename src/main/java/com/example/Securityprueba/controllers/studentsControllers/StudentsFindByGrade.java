package com.example.Securityprueba.controllers.studentsControllers;

import com.example.Securityprueba.Dto.StudentsDTO.StudentsDto;
import com.example.Securityprueba.entities.UserModels.Students;
import com.example.Securityprueba.entities.UserModels.Users;
import com.example.Securityprueba.repositories.UserRepositories.StudentsRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api/v1/students1")
public class StudentsFindByGrade {


    private final StudentsRepository studentRepository;

    public StudentsFindByGrade(StudentsRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    @GetMapping("/students/findByGrade/{grade}")
    public ResponseEntity<?> findByGrade(@PathVariable Integer grade) {
        List<Students> students = studentRepository.findAllByGrade(grade); // Get students by grade

        if (students.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        List<StudentsDto> studentDtos = mapStudentsToDto(students);

        return ResponseEntity.ok(studentDtos);
    }

    private List<StudentsDto> mapStudentsToDto(List<Students> students) {
        List<StudentsDto> studentDtos = new ArrayList<>();
        for (Students student : students) {
            StudentsDto studentDto = new StudentsDto();
            studentDto.setName(student.getName());
            studentDto.setLastName(student.getLastName());
            studentDto.setGrade(String.valueOf(student.getGrade()));
            studentDto.setIdentification(student.getIdentification());
            // Add more assignments as needed
            studentDtos.add(studentDto);
        }
        return studentDtos;
    }}

