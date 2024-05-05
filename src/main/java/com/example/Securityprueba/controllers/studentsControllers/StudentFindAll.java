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
@RestController
@RequestMapping(value = "/api/v1/students1")
@CrossOrigin(origins ="http://localhost:5173/")
public class StudentFindAll {
    @Autowired
    private StudentsRepository studentsRepository;

    @GetMapping("/students1/findAll")
    public ResponseEntity<?> responseEntity() {
        List<Users> users = studentsRepository.findAll(); // Obtain users

        if (users.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        List<StudentDtoStateVotation> studentDTOs = mapListToDTO(users);

        return ResponseEntity.ok(studentDTOs);
    }

    private List<StudentDtoStateVotation> mapListToDTO(List<Users> users) {
        List<StudentDtoStateVotation> studentDTOs = new ArrayList<>();
        for (Users user : users) {
            if (user instanceof Students) { // Check if it's an instance of Students
                Students student = (Students) user; // Cast to Students
                StudentDtoStateVotation studentDTO = new StudentDtoStateVotation();
                studentDTO.setName(student.getName());
                studentDTO.setLastName(student.getLastName());
<<<<<<< HEAD
                studentDTO.setGrade(student.getGrade());
                studentDTO.setGrade((student.getGrade()));
=======
>>>>>>> 3677e170955fd06c683f7a7718fd8e729eed0dfd
                studentDTO.setIdentification(student.getIdentification());
                studentDTO.setCode(student.getCode());
                studentDTO.setStateVotation(student.getStateVotation());

                // Check for null before setting grade
                if (student.getGrade() != null) {
                    studentDTO.setGrade(student.getGrade());
                } else {
                    // Set a default value or handle accordingly
                    studentDTO.setGrade(0); // Example: Set to 0 if grade is null
                }

                // Add more assignments as needed
                studentDTOs.add(studentDTO);
            }
        }
        return studentDTOs;
    }
}
