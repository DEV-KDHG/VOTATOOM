package com.example.Securityprueba.controllers.VotesController;


import com.example.Securityprueba.Dto.VotesDto.VotesDto;
import com.example.Securityprueba.entities.UserModels.Students;
import com.example.Securityprueba.entities.UserModels.Users;
import com.example.Securityprueba.entities.candidatesModels.Representative;
import com.example.Securityprueba.entities.votesModels.Votes;
import com.example.Securityprueba.repositories.UserRepositories.StudentsRepository;
import com.example.Securityprueba.repositories.UserRepositories.UserRepository;
import com.example.Securityprueba.service.representativeServices.RepresentativeServicesImpl;
import com.example.Securityprueba.service.votesServices.VotesSeriviceImpl;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.security.Principal;
import java.util.Optional;
@CrossOrigin(origins = "*")
@RestController

public class SaveVotesController {

    @Autowired
    private VotesSeriviceImpl votesSerivice;

    @Autowired
    private StudentsRepository studentsRepository;
    @Autowired
    private  RepresentativeServicesImpl representativeServices;


    @PostMapping(value = "/vote")
    public ResponseEntity<?> saveVote(@RequestBody VotesDto votesDto, Principal principal) {
        String username = principal.getName();
        Optional<Users> searchingUser = studentsRepository.findByUsername(username);

        if (searchingUser.isPresent()) {
            Users user = searchingUser.get();
            Optional<Students> findByIdStudents = studentsRepository.findStudentByIdentification(user.getIdentification());

            if (findByIdStudents.isPresent()) {
                Long studentId = findByIdStudents.get().getId();
                Integer gradeOfStudent = findByIdStudents.get().getGrade();

                Optional<Votes> existingVotes = votesSerivice.findBystudentsId(studentId);
                if (existingVotes.isPresent()) {
                    return ResponseEntity.ok("No se puede votar de nuevo debido a que ya existe un registro en la base de datos");
                }

                Optional<Representative> findByIdRepresentative = representativeServices.findById(votesDto.getRepresentative().getId());
                if (findByIdRepresentative.isPresent()) {
                    Integer gradeRepresentative = findByIdRepresentative.get().getGrade();
                    if (gradeOfStudent.equals(gradeRepresentative)) {
                        Votes votes = Votes.builder()
                                .comptroller(votesDto.getComptroller())
                                .personero(votesDto.getPersonero())
                                .studentsId(studentId)
                                .representative(votesDto.getRepresentative())
                                .build();

                        votesSerivice.save(votes);

                        // Actualizar estado de votación del estudiante
                        Students studentToUpdate = findByIdStudents.get();
                        studentToUpdate.setStateVotation(true);
                        studentsRepository.save(studentToUpdate);

                        return ResponseEntity.ok("Registro de voto exitoso");
                    } else {
                        return ResponseEntity.ok("El grado del representante no es igual al del estudiante");
                    }
                }
            }
        }
        return ResponseEntity.notFound().build();
    }}
