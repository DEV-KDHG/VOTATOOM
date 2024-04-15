package com.example.Securityprueba.dao.votesDao;

import com.example.Securityprueba.entities.votesModels.Votes;

import java.util.List;
import java.util.Optional;

public interface VotesDao {
void save (Votes votes);
      List<Object[]> findPersoneroVotesOrderByVoteCountDesc();
Optional<Votes>findBystudentsId(Long studentsId);
}
