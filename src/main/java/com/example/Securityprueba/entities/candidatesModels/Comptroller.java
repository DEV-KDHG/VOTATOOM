package com.example.Securityprueba.entities.candidatesModels;

import com.example.Securityprueba.entities.candidatesModels.Candidates;
import com.example.Securityprueba.entities.votesModels.Votes;
import com.example.Securityprueba.service.votesServices.VotesSeriviceImpl;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "comptroller") // Opcional: puedes especificar el nombre de la tabla si quieres que sea diferente al nombre de la clase
public class Comptroller extends Candidates {

private String estado;
private Long studentsId;

    @OneToMany(targetEntity = Votes.class, cascade = CascadeType.ALL,
            fetch = FetchType.LAZY , mappedBy = "comptroller" ,orphanRemoval = true)

    private List<Votes> votes;

    public Comptroller() {

        super();
    }


}
