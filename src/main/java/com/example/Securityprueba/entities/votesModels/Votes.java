package com.example.Securityprueba.entities.votesModels;

import com.example.Securityprueba.entities.candidatesModels.Comptroller;
import com.example.Securityprueba.entities.candidatesModels.Personero;
import com.example.Securityprueba.entities.candidatesModels.Representative;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Votes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id ;

    private  Long studentsId;

    @ManyToOne(targetEntity = Personero.class)
    @JoinColumn(name = "id_personero",nullable = false)
    @JsonIgnore
        private Personero personero;
    @ManyToOne(targetEntity = Representative.class)
    @JoinColumn(name = "id_representate",nullable = false)
    private Representative representative;

    @ManyToOne(targetEntity = Comptroller.class)
    @JoinColumn(name = "id_comptroller",nullable = false)
    @JsonIgnore
    private Comptroller comptroller;

}
