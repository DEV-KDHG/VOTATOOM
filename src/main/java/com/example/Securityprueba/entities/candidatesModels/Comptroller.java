package com.example.Securityprueba.entities.candidatesModels;

import com.example.Securityprueba.entities.candidatesModels.Candidates;
import com.example.Securityprueba.entities.votesModels.Votes;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "comptroller") // Opcional: puedes especificar el nombre de la tabla si quieres que sea diferente al nombre de la clase
public class Comptroller extends Candidates {



    public Comptroller() {
        // Puedes llamar al constructor de la superclase si es necesario
        super();
    }

    @OneToMany(mappedBy = "comptroller",targetEntity = Votes.class ,cascade = CascadeType.ALL , fetch = FetchType.LAZY,orphanRemoval = true)
    @JsonIgnore
    private List<Votes> votesList;
}
