package com.example.Securityprueba.service.votesServices;

import com.example.Securityprueba.entities.votesModels.Votes;

import java.util.Optional;

public interface VotosService {
    void save (Votes votes);
    Optional<Votes> findBystudentsId(Long studentsId);


}
