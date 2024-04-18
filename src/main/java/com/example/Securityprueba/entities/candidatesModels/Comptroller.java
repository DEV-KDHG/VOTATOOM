package com.example.Securityprueba.entities.candidatesModels;

import com.example.Securityprueba.entities.candidatesModels.Candidates;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "comptroller") // Opcional: puedes especificar el nombre de la tabla si quieres que sea diferente al nombre de la clase
public class Comptroller extends Candidates {



    public Comptroller() {
        // Puedes llamar al constructor de la superclase si es necesario
        super();
    }


}
