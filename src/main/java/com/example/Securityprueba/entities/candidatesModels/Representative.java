package com.example.Securityprueba.entities.candidatesModels;

import com.example.Securityprueba.entities.votesModels.Votes;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "representative")
public class Representative extends Candidates{

    public Representative() {
        // Lógica de inicialización del objeto Representative
    }
    @OneToMany(mappedBy = "representative",targetEntity = Votes.class ,cascade = CascadeType.ALL , fetch = FetchType.LAZY,orphanRemoval = true)
    @JsonIgnore
    private List<Votes> votesList;

}
