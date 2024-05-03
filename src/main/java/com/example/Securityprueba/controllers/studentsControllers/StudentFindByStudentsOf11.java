package com.example.Securityprueba.controllers.studentsControllers;

import com.example.Securityprueba.Dto.StudentsDTO.StudentsDto;
import com.example.Securityprueba.entities.UserModels.Students;
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
public class StudentFindByStudentsOf11 {
    private final StudentsRepository studentRepository;
    public StudentFindByStudentsOf11(StudentsRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    @GetMapping("/students/findByGradeOf11")
    public ResponseEntity<?> findByGradeOf11() {
        List<Students> students = studentRepository.findAllByGrade(11); // Get students by grade

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
            studentDto.setGroup(student.getGroup());
            // Add more assignments as needed
            studentDtos.add(studentDto);
        }
        return studentDtos;
    }}
