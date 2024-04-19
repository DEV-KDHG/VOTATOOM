package com.example.Securityprueba.controllers.VotesController;


import com.example.Securityprueba.service.votesServices.VotesSeriviceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/votes")
public class CountVotesByRepresentativesOrderByGradoDescController {

    @Autowired
    private VotesSeriviceImpl  votesSerivice;

    @GetMapping("/representatives-by-grado-desc")
    public ResponseEntity<List<Map<String, Object>>> countVotesByRepresentativesOrderByGradoDesc() {
        List<Object[]> results = votesSerivice.countVotesByRepresentativesOrderByGradoDesc();
        List<Map<String, Object>> mappedResults = mapResultsToResponse(results);
        return ResponseEntity.ok(mappedResults);
    }

    private List<Map<String, Object>> mapResultsToResponse(List<Object[]> results) {
        return results.stream().map(row -> {
            Map<String, Object> mappedRow = Map.of(
                    "grado", row[0],
                    "name", row[1],
                    "identification", row[2],
                    "voteCount", row[3]
            );
            return mappedRow;
        }).collect(Collectors.toList());
    }
}