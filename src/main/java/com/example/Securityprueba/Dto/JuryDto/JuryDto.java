package com.example.Securityprueba.Dto.JuryDto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JuryDto {

    private String name;
    private Long identification;
     private String lastName;

}
