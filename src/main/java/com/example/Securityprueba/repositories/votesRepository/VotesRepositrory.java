package com.example.Securityprueba.repositories.votesRepository;

import com.example.Securityprueba.entities.votesModels.Votes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VotesRepositrory  extends JpaRepository<Votes,Long> {
    Optional<Votes> findBystudentsId(Long studentsId);

    @Query(value = "SELECT c.name AS name, c.identification AS identification, COUNT(*) AS voteCount " +
            "FROM Votes v " +
            "JOIN v.personero c " +  // Utiliza la relación entre Votes y Personero (o candidato)
            "GROUP BY c.name, c.identification")
    List<Object[]> countVotesByPersoneros();

    @Query(value = "SELECT c.name AS name, c.identification AS identification, COUNT(*) AS voteCount " +
            "FROM Votes v " +
            "JOIN v.comptroller c " +  // Utiliza la relación entre Votes y Comptroller
            "GROUP BY c.name, c.identification")
    List<Object[]> countVotesByComptrollers();

    @Query(value = "SELECT r.grade AS grado, r.name AS name, r.identification AS identification, COUNT(*) AS voteCount " +
            "FROM Votes v " +
            "JOIN v.representative r " +  // Utiliza la relación entre Votes y Representative
            "GROUP BY r.grade, r.name, r.identification " +
            "ORDER BY r.grade DESC")
    List<Object[]> countVotesByRepresentativesOrderByGradoDesc();
}
