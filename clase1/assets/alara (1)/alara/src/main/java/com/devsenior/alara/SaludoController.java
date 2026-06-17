package com.devsenior.alara;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SaludoController {

    @GetMapping("/")
    public String prueba(){
        return "Hola mundo web Spring Boot";
    }

    @GetMapping("/saludo")
    public String saludo(){
        return "Bienvenido";
    }

    @GetMapping("/java")
    public String java(){
        return "Estoy aprendiendo Spring Boot";
    }
}
