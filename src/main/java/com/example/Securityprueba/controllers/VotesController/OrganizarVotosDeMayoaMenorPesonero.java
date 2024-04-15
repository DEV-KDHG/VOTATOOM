package com.example.Securityprueba.controllers.VotesController;

import com.example.Securityprueba.service.votesServices.VotesSeriviceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/ordenarDeMayorAMenor")
public class OrganizarVotosDeMayoaMenorPesonero {
@Autowired
private VotesSeriviceImpl votesSerivice;


    @GetMapping("/personero")
    public ResponseEntity<List<Object[]>> getRepresentativeVotes() {
        List<Object[]> personeroVotesOrderByVoteCountDesc = votesSerivice.findPersoneroVotesOrderByVoteCountDesc();

        if (personeroVotesOrderByVoteCountDesc.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(personeroVotesOrderByVoteCountDesc);
    }
}
