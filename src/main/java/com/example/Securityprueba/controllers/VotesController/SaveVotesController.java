package com.example.Securityprueba.controllers.VotesController;

import com.example.Securityprueba.Dto.VotesDto;
import com.example.Securityprueba.entities.Students;
import com.example.Securityprueba.entities.Users;
import com.example.Securityprueba.entities.Votes;
import com.example.Securityprueba.repository.StudentsRepository;
import com.example.Securityprueba.repository.UserRepository;
import com.example.Securityprueba.service.votesServices.VotesSeriviceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;
import java.security.Principal;
import java.util.Optional;

@RestController

public class SaveVotesController {

    @Autowired
    private    VotesSeriviceImpl votesSerivice;

    @Autowired
    private StudentsRepository studentsRepository;
    @PostMapping("/votes")
    public ResponseEntity<?> save(@RequestBody VotesDto votesDto, Principal principal) throws URISyntaxException {
        String username = principal.getName(); // Obtener el nombre de usuario del usuario autenticado

        //if (studentOptional != null && studentOptional.get() != null)/

        // Buscar el usuario por su nombre de usuario
        Optional<Users> userOptional = studentsRepository.findByUsername(username);
        Long idVotess= userOptional.get().getId();

        Optional<Votes>optional=votesSerivice.findBystudentsId(idVotess);
        if (userOptional.isPresent() &&!optional.isPresent()) {

            Votes votes = Votes.builder()
                    .stateVotation(votesDto.getStateVotation())
                    .studentsId(idVotess)
                    .build();

            votesSerivice.save(votes);
            return ResponseEntity.created(new URI("/votes")).build();

        }


        return            ResponseEntity.ok(" no se puede crear debido a que ya existe el voto");

    }}
