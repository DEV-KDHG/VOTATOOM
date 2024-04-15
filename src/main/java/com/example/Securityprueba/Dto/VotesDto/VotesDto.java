package com.example.Securityprueba.Dto.VotesDto;

import com.example.Securityprueba.entities.UserModels.Students;
import com.example.Securityprueba.entities.candidatesModels.Comptroller;
import com.example.Securityprueba.entities.candidatesModels.Personero;
import com.example.Securityprueba.entities.candidatesModels.Representative;
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
    private Personero personero;

    private Comptroller comptroller;
    private Representative representative;
    private  Students students;
}
