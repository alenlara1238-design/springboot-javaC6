package com.devsenior.democapas.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devsenior.democapas.model.Cancion;
import com.devsenior.democapas.service.CancionService;

@RestController
@RequestMapping("/canciones")
public class CancionController {
    private final CancionService service;

    public CancionController(CancionService service){
        this.service = service;
    }

    @GetMapping("/destacada")
    public String obtenerCancion(){
        return service
                .obtenerCancionDestacada()
                .getTitulo();
    }

    @PostMapping
    public String crearCancion(@RequestBody Cancion nuevaCancion){
        this.service.crearCancion(nuevaCancion);
        return "Cancion " + nuevaCancion.getTitulo() + " creada exitosamente";
    }


    @GetMapping("/buscar")
    public List<Cancion> buscarCanciones(@RequestParam String palabra){
        return this.service.buscarCancionesPorPalabra(palabra);
    }


}
