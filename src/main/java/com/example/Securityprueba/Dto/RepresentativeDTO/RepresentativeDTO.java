package com.example.Securityprueba.Dto.RepresentativeDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@Data
@NoArgsConstructor
public class RepresentativeDTO {

    private String name;
    private String lastName;
    private Long identification;
    private Integer grade;
    private byte[] data;

    private String photo;
    private String group;


}


