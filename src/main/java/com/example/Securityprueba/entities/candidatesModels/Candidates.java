package com.example.Securityprueba.entities.candidatesModels;

import jakarta.persistence.*;
import lombok.*;


@MappedSuperclass
@Data
@NoArgsConstructor(force = true, access = AccessLevel.PROTECTED)
@AllArgsConstructor

public class Candidates {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
private  Long id;
    private String name;


    private String lastName;
    private Long identification;
    private Integer grade;
    private String photo;
    @Column(name = "`group`")
    private String group;


}
