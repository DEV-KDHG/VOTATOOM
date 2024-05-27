package com.example.Securityprueba.controllers.AuntheticationControllers;

import com.example.Securityprueba.Dto.JuryDto.JuryDto;
import com.example.Securityprueba.Dto.StudentsDTO.StudentDtoStateVotation;
import com.example.Securityprueba.entities.UserModels.Jury;
import com.example.Securityprueba.entities.UserModels.Students;
import com.example.Securityprueba.entities.UserModels.Users;
import com.example.Securityprueba.repositories.UserRepositories.JuryRepository;
import com.example.Securityprueba.repositories.UserRepositories.StudentsRepository;
import com.example.Securityprueba.service.userServices.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*")
public class DemoController {



    private final JuryRepository juryRepository;
    private final AuthenticationService authenticationService;

    public DemoController(JuryRepository juryRepository, AuthenticationService authenticationService) {
        this.juryRepository = juryRepository;
        this.authenticationService = authenticationService;
    }

    @GetMapping("/hello")

    public ResponseEntity<String> demo() {
        return ResponseEntity.ok("hello security");
    }

    @GetMapping("/admin/hello")

    public ResponseEntity<String> demo2() {
        return ResponseEntity.ok("hello security admin");
    }

@GetMapping("/jury/hello")

public ResponseEntity<String> demo3 () {
    return ResponseEntity.ok("hello security jury");

}

    @DeleteMapping("/deleteByIdentification/{identification}")
    @Transactional
    public ResponseEntity<String> deleteByIdentification(@PathVariable Long identification) {
        try {
            Optional<Jury> juryOptional = juryRepository.findJuryByIdentification(identification);
            if (juryOptional.isPresent()) {
                Jury jury = juryOptional.get();

                // Ejemplo de acceso a propiedades del jurado
                System.out.println("Jurado encontrado: " + jury.getIdentification());
                System.out.println("Jurado encontrado: " + jury.getName());

                // Eliminar el jurado
                authenticationService.deleteJuryByIdentification(jury.getIdentification());
                juryRepository.deleteByIdentification(identification);
                return ResponseEntity.ok("Se eliminó el jurado con Identificación: " + identification);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al eliminar el jurado: " + e.getMessage());
        }
        return ResponseEntity.notFound().build();
    }



    @GetMapping("/api/v2/findAll/jury")
    public ResponseEntity<?> responseEntity() {
        List<Users> users = juryRepository.findAll(); // Obtain users

        if (users.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        List<JuryDto> studentDTOs = mapListToDTO(users);

        return ResponseEntity.ok(studentDTOs);
    }

    private List<JuryDto> mapListToDTO(List<Users> users) {
        List<JuryDto> juryDtos = new ArrayList<>();
        for (Users user : users) {
            if (user instanceof Jury) { // Check if it's an instance of Students
                Jury student = (Jury) user; // Cast to Students
                JuryDto juryDto = new JuryDto();
                juryDto.setName(student.getName());
                juryDto.setLastName(student.getLastName());

                juryDto.setIdentification(student.getIdentification());


                // Check for null before setting grade

                // Add more assignments as needed
                juryDtos.add(juryDto);
            }
        }
        return juryDtos;
    }
}
