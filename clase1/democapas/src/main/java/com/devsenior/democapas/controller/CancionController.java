package com.devsenior.democapas.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsenior.democapas.service.CancionService;

@RestController
public class CancionController {
    private final CancionService service;

    public CancionController(CancionService service){
        this.service = service;
    }

    @GetMapping("/cancion")
    public String obtenerCancion(){
        return service
                .obtenerCancionDestacada()
                .getTitulo();
    }




}
