package com.example.Securityprueba.entities.candidatesModels;

import com.example.Securityprueba.entities.votesModels.Votes;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table
@Data


public class Personero  extends  Candidates{
    public Personero() {
        // Puedes llamar al constructor de la superclase si es necesario
        super();
    }
    @OneToMany(mappedBy = "personero",targetEntity = Votes.class ,cascade = CascadeType.ALL , fetch = FetchType.LAZY,orphanRemoval = true)
    @JsonIgnore
    private List<Votes> votesList;
}
