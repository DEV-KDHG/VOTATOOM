package com.example.Securityprueba.controllers.VotesController;

import com.example.Securityprueba.repositories.votesRepository.VotesRepositrory;
import com.example.Securityprueba.service.votesServices.VotesSeriviceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/votes")
public class CountVotesByPersoneros {
@Autowired
private VotesSeriviceImpl votesSerivice;
    @GetMapping("/personero")
    public List<Map<String, Object>> getVotesByPersoneros() {
        List<Object[]> results = votesSerivice.countVotesByPersoneros();
        return mapResultsToResponse(results);
    }

    // Método auxiliar para mapear los resultados de la consulta a un formato de respuesta
    private List<Map<String, Object>> mapResultsToResponse(List<Object[]> results) {
        return results.stream().map(row -> {
            Map<String, Object> mappedRow = Map.of(
                    "name", row[0],
                    "identification", row[1],
                    "voteCount", row[2]
            );
            return mappedRow;
        }).collect(Collectors.toList());
    }

}
