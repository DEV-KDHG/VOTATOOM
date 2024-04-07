package com.example.Securityprueba.controllers.PersonController;

import com.example.Securityprueba.entities.candidatesModels.Personero;
import com.example.Securityprueba.service.PersoneroServices.PersonerosServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/personero")
public class PersonDeleteById {

    @Autowired
    private PersonerosServicesImpl personerosServices;

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deletePersoneroById(@PathVariable Long id) {
        // Buscar el Personero por su identificación
        Personero personero = personerosServices.findById(id).orElse(null);

        if (personero == null) {
            // Si no se encuentra el Personero, retornar un error 404 Not Found
            return ResponseEntity.notFound().build();
        }

        // Eliminar el Personero encontrado
        personerosServices.deleteById(personero.getId());

        // Retornar una respuesta 200 OK indicando que el Personero fue eliminado exitosamente
        return ResponseEntity.ok("Personero eliminado correctamente");
    }
}