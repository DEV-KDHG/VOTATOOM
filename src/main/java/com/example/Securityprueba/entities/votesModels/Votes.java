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

@ManyToOne(targetEntity = Personero.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name ="id_personero")
@JsonIgnore
    private  Personero personero;

//    @ManyToOne(targetEntity = Comptroller.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JoinColumn(name ="id_controller",nullable = false)
//    @JsonIgnore
//    private Comptroller comptroller;
//
//    @ManyToOne(targetEntity = Representative.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JoinColumn(name ="id_representate",nullable = false)
//    @JsonIgnore
//
//    private Representative representative;

}
