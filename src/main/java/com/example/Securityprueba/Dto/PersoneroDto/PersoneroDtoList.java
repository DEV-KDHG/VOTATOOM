package com.example.Securityprueba.Dto.PersoneroDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PersoneroDtoList {

  private Long id;

        private String name;

        private byte[] data;

        private String lastName;
        private Long identification;
        private Integer grade;

        private String group;



}
