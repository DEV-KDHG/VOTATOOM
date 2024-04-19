package com.example.Securityprueba.dao.votesDao;

import com.example.Securityprueba.entities.votesModels.Votes;

import java.util.List;
import java.util.Optional;

public interface VotesDao {
void save (Votes votes);
     Optional<Votes> findBystudentsId(Long studentsId);
      List<Object[]> countVotesByPersoneros();
      List<Object[]> countVotesByComptrollers();

      List<Object[]> countVotesByRepresentativesOrderByGradoDesc();
}
