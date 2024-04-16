package com.example.Securityprueba.Dto.StudentsDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentDtoStateVotation {
    private String name;
    private  Boolean stateVotation;

    private String lastName;
    private int grade;

    private Long identification;
}
