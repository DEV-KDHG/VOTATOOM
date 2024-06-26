package com.example.Securityprueba.service.votesServices;

import com.example.Securityprueba.dao.votesDao.VotesDaoImpl;
import com.example.Securityprueba.entities.votesModels.Votes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class   VotesSeriviceImpl implements  VotosService{
    @Autowired
    private VotesDaoImpl votesDao;

    @Transactional()
    @Override
    public void save(Votes votes) {
        votesDao.save(votes);
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Votes> findBystudentsId(Long studentsId) {
return votesDao.findBystudentsId(studentsId);
    }
@Transactional()
    @Override
    public List<Object[]> countVotesByPersoneros() {
        return votesDao.countVotesByPersoneros();
    }
    @Transactional()
    @Override
    public List<Object[]> countVotesByComptrollers() {
        return votesDao.countVotesByComptrollers();
    }
    @Transactional()
    @Override
    public List<Object[]> countVotesByRepresentativesOrderByGradoDesc() {
        return votesDao.countVotesByRepresentativesOrderByGradoDesc();
    }


}
