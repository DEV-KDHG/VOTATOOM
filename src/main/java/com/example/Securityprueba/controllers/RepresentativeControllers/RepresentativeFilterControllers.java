package com.example.Securityprueba.controllers.RepresentativeControllers;

import com.example.Securityprueba.Dto.PersoneroDto.PersoneroDTO;
import com.example.Securityprueba.Dto.RepresentativeDTO.RepresentativeDTO;
import com.example.Securityprueba.entities.CustomPrincipal;
import com.example.Securityprueba.entities.UserModels.Students;
import com.example.Securityprueba.entities.UserModels.Users;
import com.example.Securityprueba.entities.candidatesModels.Representative;
import com.example.Securityprueba.repositories.CandidatesRepository.RepresentativeRepository;
import com.example.Securityprueba.repositories.UserRepositories.StudentsRepository;
import com.example.Securityprueba.repositories.UserRepositories.UserRepository;
import com.example.Securityprueba.service.representativeServices.RepresentativeServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yaml.snakeyaml.representer.Represent;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api/v1/representative")
public class RepresentativeFilterControllers {

    @Autowired
    private StudentsRepository studentsRepository;

    @Autowired
    private RepresentativeServicesImpl representativeServices;

    @Autowired
    private RepresentativeRepository representativeRepository;


        @GetMapping("/findAllGrade")
        public ResponseEntity<?> findAllByGrade(Principal principal) {
            try {
                // Obtener el nombre de usuario del principal
                String username = principal.getName();
                System.out.println("Nombre de usuario: " + username);

                // Buscar al usuario por su nombre de usuario
                Optional<Users> optionalUser = studentsRepository.findByUsername(username);

                if (optionalUser.isPresent()) {
                    Users user = optionalUser.get();

                    // Buscar al estudiante por su identificación
                    Optional<Students> optionalStudent = studentsRepository.findStudentByIdentification(user.getIdentification());

                    if (optionalStudent.isPresent()) {
                        Students student = optionalStudent.get();
                        Integer grade = student.getGrade();

                        // Buscar representantes por grado
                        List<RepresentativeDTO> representativeDTOs = representativeRepository.findAllByGrade(grade)
                                .stream()
                                .map(representative -> RepresentativeDTO.builder()
                                        .name(representative.getName())
                                        .photo(representative.getPhoto())
                                        .identification(representative.getIdentification())
                                        .grade(representative.getGrade())
                                        .group(representative.getGroup())
                                        .build())
                                .collect(Collectors.toList());

                        return ResponseEntity.ok(representativeDTOs);
                    } else {
                        return ResponseEntity.notFound().build(); // No se encontró al estudiante
                    }
                } else {
                    return ResponseEntity.notFound().build(); // No se encontró al usuario
                }
            } catch (Exception e) {
                // Manejar excepciones
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error en el servidor.");
            }

    }}