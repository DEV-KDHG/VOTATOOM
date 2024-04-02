package com.example.Securityprueba.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@MappedSuperclass
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Candidate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
private  Long id;
    private String name;

    @Column(name = "last_name")
    private String lastName;
    private Long identification;
    private Integer grado;
    private String grupo;
    private String foto;




}
