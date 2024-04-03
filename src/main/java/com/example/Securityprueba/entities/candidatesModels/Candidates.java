package com.example.Securityprueba.entities.candidatesModels;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@MappedSuperclass
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Candidates {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
private  Long id;
    private String name;


    private String lastName;
    private Long identification;
    private Integer grade;
    private String photo;




}
