package com.example.Securityprueba.repositories.votesRepository;

import com.example.Securityprueba.entities.votesModels.Votes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VotesRepositrory  extends JpaRepository<Votes,Long> {
    Optional<Votes> findBystudentsId(Long studentsId);
}
