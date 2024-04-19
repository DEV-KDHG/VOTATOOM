package com.example.Securityprueba.dao.votesDao;

import com.example.Securityprueba.entities.votesModels.Votes;
import com.example.Securityprueba.repositories.votesRepository.VotesRepositrory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class VotesDaoImpl  implements  VotesDao{
@Autowired
    private VotesRepositrory votesRepositrory;

    @Override
    public void save(Votes votes) {
        votesRepositrory.save(votes);
    }



    @Override
    public Optional<Votes> findBystudentsId(Long studentsId) {
        return votesRepositrory.findBystudentsId(studentsId);
    }

    @Override
    public List<Object[]> countVotesByPersoneros() {
        return votesRepositrory.countVotesByPersoneros();
    }

    @Override
    public List<Object[]> countVotesByComptrollers() {
        return votesRepositrory.countVotesByComptrollers();
    }

    @Override
    public List<Object[]> countVotesByRepresentativesOrderByGradoDesc() {
        return votesRepositrory.countVotesByRepresentativesOrderByGradoDesc();
    }
}
