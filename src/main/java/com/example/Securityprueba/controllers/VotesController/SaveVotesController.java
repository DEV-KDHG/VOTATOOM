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
    private RepresentativeServicesImpl representativeServicesM;

@PostMapping("/jaja")

public ResponseEntity<?>guadar(@RequestBody Votes votesGet, Principal principal){
    String username = principal.getName();
    Optional<Users>users=studentsRepository.findByUsername(username);
    Votes votes = new Votes();
    votes.setPersonero(votesGet.getPersonero());
    votes.setStudentsId(users.get().getId());
    votesSerivice.save(votes);
    return ResponseEntity.ok("se guardo");
}
}