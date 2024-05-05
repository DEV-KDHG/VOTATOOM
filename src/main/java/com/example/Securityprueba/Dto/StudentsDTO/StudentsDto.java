package com.example.Securityprueba.Dto.StudentsDTO;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentsDto {
    private String name;
    private  Boolean stateVotation;
    private  String group;
    private String lastName;
    private String grade;
    private Long identification;
    private String code;
}
