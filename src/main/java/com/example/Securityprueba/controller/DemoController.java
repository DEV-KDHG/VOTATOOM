package com.example.Securityprueba.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class DemoController {

    @GetMapping("/hello")

    public ResponseEntity<String> demo() {
        return ResponseEntity.ok("hello security");
    }

    @GetMapping("/admin/hello")

    public ResponseEntity<String> demo2() {
        return ResponseEntity.ok("hello security admin");
    }

@GetMapping("/jury/hello")

public ResponseEntity<String> demo3 (){
    return ResponseEntity.ok("hello security jury");
}
}