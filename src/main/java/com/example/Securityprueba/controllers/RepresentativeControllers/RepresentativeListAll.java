package com.example.Securityprueba.controllers.RepresentativeControllers;

        import com.example.Securityprueba.Dto.RepresentativeDTO.RepresentativeDTO;
        import com.example.Securityprueba.entities.candidatesModels.Representative;
        import com.example.Securityprueba.service.representativeServices.RepresentativeServicesImpl;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.http.ResponseEntity;
        import org.springframework.web.bind.annotation.CrossOrigin;
        import org.springframework.web.bind.annotation.GetMapping;
        import org.springframework.web.bind.annotation.RequestMapping;
        import org.springframework.web.bind.annotation.RestController;

        import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value ="/api/v1/representative")
public class RepresentativeListAll {

    @Autowired
    private RepresentativeServicesImpl representativeServices;

    @GetMapping("/findAll")
    public List<RepresentativeDTO> findAll(){
        return representativeServices.findAll();

    }
}
