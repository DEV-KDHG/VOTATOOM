package com.example.Securityprueba.dao.votesDao;

import com.example.Securityprueba.entities.Votes;

import java.util.Optional;

public interface VotesDao {
void save (Votes votes);

Optional<Votes>findBystudentsId(Long studentsId);
}
