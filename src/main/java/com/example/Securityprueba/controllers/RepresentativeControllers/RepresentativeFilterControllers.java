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
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yaml.snakeyaml.representer.Represent;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/representative")
public class RepresentativeFilterControllers {

    @Autowired
    private StudentsRepository studentsRepository;

    @Autowired
    private RepresentativeServicesImpl representativeServices;

    @Autowired
    private RepresentativeRepository representativeRepository;

    @GetMapping(value = "/findAllGrade")
    public ResponseEntity<?> findAllByGrade(@AuthenticationPrincipal CustomPrincipal principal) {
        Long identification = principal.getIdentification();

        Optional<Users> optionalUsers = studentsRepository.findUserByIdentification(identification);

        if (optionalUsers.isPresent()) {
            Optional<Students> students = studentsRepository.findStudentByIdentification(optionalUsers.get().getIdentification());

            if (students.isPresent()) {
                Integer grade = students.get().getGrade();

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
    }
}
