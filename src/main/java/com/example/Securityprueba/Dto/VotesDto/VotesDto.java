package com.example.Securityprueba.Dto.VotesDto;

import com.example.Securityprueba.entities.UserModels.Students;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor

public class VotesDto {

    private  Long id ;

    private  String stateVotation;

    private  Students students;
}
