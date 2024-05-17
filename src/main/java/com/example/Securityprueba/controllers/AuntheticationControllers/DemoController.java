package com.example.Securityprueba.controllers.AuntheticationControllers;

import com.example.Securityprueba.Dto.JuryDto.JuryDto;
import com.example.Securityprueba.entities.UserModels.Jury;
import com.example.Securityprueba.repositories.UserRepositories.JuryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
public class DemoController {

    @Autowired

    private JuryRepository juryRepository;

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

    @DeleteMapping("/api/v2/delete/jury/{identification}")


public  ResponseEntity<?>deleteById (@PathVariable Long identification){

        Optional<Jury>optionalJury = juryRepository.findByIdentification(identification);

        if (optionalJury.isPresent())
        {

            juryRepository.deleteById(identification);

            return new ResponseEntity<>(HttpStatus.OK);
        }
        else {
            return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }


    }
    @GetMapping("/api/v2/findAll/jury")


    public  ResponseEntity<?>findAll(){
        List<JuryDto> juryDtos = juryRepository.findAll().stream()
                .map(jury -> JuryDto.builder()
                        .name(jury.getName())
                        .lastName(jury.getLastName())
                        .identification(jury.getIdentification())
                        .build())
                .toList();


        return  new ResponseEntity<>(juryDtos, HttpStatus.OK);
    }



}


