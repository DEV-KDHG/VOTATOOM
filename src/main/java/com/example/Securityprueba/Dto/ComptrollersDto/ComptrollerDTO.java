package com.example.Securityprueba.Dto.ComptrollersDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComptrollerDTO {
    private String name;
    private String lastName;
    private Long identification;
    private Integer grade;
    private String estado;
    private byte[] data;
    private String group;

}
