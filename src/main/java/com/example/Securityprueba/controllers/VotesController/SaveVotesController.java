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
Optional<Users>searchingName =studentsRepository.findByUsername(username);
if (searchingName.isPresent()){
    Users user=searchingName.get();
    Optional<Students>findByIdStudents=studentsRepository.findStudentByIdentification(user.getIdentification());
  Long idStundentSession = findByIdStudents.get().getId();
  Long identification = findByIdStudents.get().getIdentification();
  Integer gradeOfStudent = findByIdStudents.get().getGrade();
 Optional<Votes>optionalVotes=votesSerivice.findBystudentsId(idStundentSession);
  if (optionalVotes.isPresent()){
     return ResponseEntity.ok(" no se puede debido  ya existe en la base de datos");
 }


    Optional<Representative>findByIdRepresentative=representativeServices.findById(votesDto.getRepresentative().getId());
Integer gradeRepresentative=findByIdRepresentative.get().getGrade();
    if (gradeOfStudent==gradeRepresentative){
        Votes votes = Votes.builder()

                .comptroller(votesDto.getComptroller())
                .personero(votesDto.getPersonero())
                .studentsId(idStundentSession)
                .representative(votesDto.getRepresentative())
                .build();
        Optional<Students>students=studentsRepository.findStudentByIdentification(identification);

        Students students1 = new Students();
        students1.setStateVotation(true);

        studentsRepository.save(students1);



        votesSerivice.save(votes);
return ResponseEntity.ok("Registro de voto exitoso");
    }
    else {
        return ResponseEntity.ok("El grado del representate no es igual")
  ;  }
    }
return ResponseEntity.notFound().build();


    }


}