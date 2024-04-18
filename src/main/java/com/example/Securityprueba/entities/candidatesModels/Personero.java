package com.example.Securityprueba.entities.candidatesModels;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table
@Data


public class Personero  extends  Candidates{
    public Personero() {
        // Puedes llamar al constructor de la superclase si es necesario
        super();
    }
}
