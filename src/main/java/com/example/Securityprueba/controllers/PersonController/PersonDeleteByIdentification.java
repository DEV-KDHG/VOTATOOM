package com.example.Securityprueba.controllers.PersonController;

import com.example.Securityprueba.entities.candidatesModels.Personero;
import com.example.Securityprueba.service.PersoneroServices.PersonerosServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api/v1/personero")
public class PersonDeleteByIdentification {

    @Autowired
    private PersonerosServicesImpl personerosServices;

    @DeleteMapping("/delete/{identification}")
    public ResponseEntity<?> deletePersoneroById(@PathVariable Long identification) {
        // Buscar el Personero por su identificación
        Personero personero = personerosServices.findByIdentification(identification).orElse(null);

        if (personero == null) {
            // Si no se encuentra el Personero, retornar un error 404 Not Found
            return ResponseEntity.notFound().build();
        }

        // Eliminar el Personero encontrado
        personerosServices.deleteByIdentificacion(personero.getIdentification());

        // Retornar una respuesta 200 OK indicando que el Personero fue eliminado exitosamente
        return ResponseEntity.ok("Personero eliminado correctamente");
    }
}