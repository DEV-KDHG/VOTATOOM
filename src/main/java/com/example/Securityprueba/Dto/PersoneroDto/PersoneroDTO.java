package com.example.Securityprueba.Dto.PersoneroDto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PersoneroDTO {
    private String name;

    private byte[] data;

    private String lastName;
    private Long identification;
    private Integer grade;

    private String group;

}
