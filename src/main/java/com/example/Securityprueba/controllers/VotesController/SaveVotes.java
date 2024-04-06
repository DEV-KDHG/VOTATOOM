package com.example.Securityprueba.controllers.VotesController;

import com.example.Securityprueba.Dto.VotesDto.VotesDto;
import com.example.Securityprueba.entities.UserModels.Students;
import com.example.Securityprueba.entities.UserModels.Users;
import com.example.Securityprueba.entities.votesModels.Votes;
import com.example.Securityprueba.repositories.UserRepositories.StudentsRepository;
import com.example.Securityprueba.service.votesServices.VotesSeriviceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;
import java.security.Principal;
import java.util.Optional;

@RestController

public class SaveVotes {

    @Autowired
    private    VotesSeriviceImpl votesSerivice;

    @Autowired
    private StudentsRepository studentsRepository;


    @PostMapping("/votes")
    public ResponseEntity<?> save(@RequestBody VotesDto votesDto, Principal principal) throws URISyntaxException {
        String username = principal.getName();
        Optional<Users> userOptional = studentsRepository.findByUsername(username);
        Long idVotess= userOptional.get().getId();



        Optional<Votes>optional=votesSerivice.findBystudentsId(idVotess);
        if (userOptional.isPresent() &&!optional.isPresent()) {

            Votes votes = Votes.builder()
                    .stateVotation(votesDto.getStateVotation())
                    .studentsId(idVotess)
                    .comptroller(votesDto.getComptroller())
                    .build();

            votesSerivice.save(votes);
            return ResponseEntity.created(new URI("/votes")).build();

        }


        return            ResponseEntity.ok(" no se puede crear debido a que ya existe el voto");

    }}