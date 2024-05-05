package com.example.Securityprueba.controllers.studentsControllers;

import com.example.Securityprueba.Dto.StudentsDTO.StudentDtoStateVotation;
import com.example.Securityprueba.entities.UserModels.Students;
import com.example.Securityprueba.repositories.UserRepositories.StudentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins ="http://localhost:5173/")

@RequestMapping(value = "/api/v1/students1")
public class StudentFinByStundet10And11 {

    @Autowired
    private StudentsRepository studentsRepository;

    @GetMapping("/students/findByGradeOf11and10")
    public ResponseEntity<List<StudentDtoStateVotation>> findAllStudents() {
        List<Students> students = studentsRepository.findAllByGrade(10); // Obtener estudiantes de grado 10

        students.addAll(studentsRepository.findAllByGrade(11)); // Agregar estudiantes de grado 11

        if (students.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        List<StudentDtoStateVotation> studentDTOs = students.stream()
                .map(this::mapStudentToDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(studentDTOs);
    }

    private StudentDtoStateVotation mapStudentToDTO(Students student) {
        StudentDtoStateVotation studentDTO = new StudentDtoStateVotation();
        studentDTO.setName(student.getName());
        studentDTO.setLastName(student.getLastName());
        studentDTO.setGrade(student.getGrade());
        studentDTO.setIdentification(student.getIdentification());
         studentDTO.setStateVotation(student.getStateVotation());
        // Añade más asignaciones según sea necesario
        return studentDTO;
    }
}
