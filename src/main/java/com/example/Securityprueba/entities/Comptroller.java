package com.example.Securityprueba.entities;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data

public class Comptroller  extends Candidate{

private String estado;
    public Comptroller() { super();
    }
}
