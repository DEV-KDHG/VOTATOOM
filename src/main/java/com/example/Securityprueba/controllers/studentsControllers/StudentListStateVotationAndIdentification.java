package com.example.Securityprueba.controllers.studentsControllers;

import com.example.Securityprueba.Dto.StudentsDTO.StudentDtoStateVotation;
import com.example.Securityprueba.Dto.StudentsDTO.StudentsDto;
import com.example.Securityprueba.entities.UserModels.Students;
 import com.example.Securityprueba.repositories.UserRepositories.StudentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1")
public class StudentListStateVotationAndIdentification {
    @Autowired
    private StudentsRepository studentsRepository;

    @GetMapping(value = "/ListStates")
    public ResponseEntity<List<StudentDtoStateVotation>> findAllStudents() {
        List<Students> students = studentsRepository.findStudentByStateVotationTrue();

        if (students.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        List<StudentDtoStateVotation> studentDTOs = mapListToDTO(students);

        return ResponseEntity.ok(studentDTOs);
    }

    private List<StudentDtoStateVotation> mapListToDTO(List<Students> students) {
        return students.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }
    private StudentDtoStateVotation mapToDTO(Students student) {
        Integer grade = student.getGrade();  // Obtener el valor de grade

        // Verificar si grade es null antes de acceder a su valor entero
        Integer gradeValue = (grade != null) ? grade : 0;  // Se puede utilizar otro valor por defecto en caso de ser null

        // Construir el objeto StudentDtoStateVotation con el valor de grade
        return StudentDtoStateVotation.builder()
                .name(student.getName())
                .lastName(student.getLastName())
                .identification(student.getIdentification())
                .stateVotation(student.getStateVotation())
                .grade(gradeValue)  // Asignar gradeValue, que ahora es Integer
                .build();
    }


}