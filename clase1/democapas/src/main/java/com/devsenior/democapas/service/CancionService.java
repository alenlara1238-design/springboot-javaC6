package com.devsenior.democapas.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.devsenior.democapas.model.Cancion;
import com.devsenior.democapas.repository.CancionRepository;

@Service
public class CancionService {

    private final CancionRepository repository;

    public CancionService(CancionRepository repository){
        this.repository = repository;
    }

    public Cancion obtenerCancionDestacada(){
        return repository.obtenerCanciones().get(0);
    }

    public void crearCancion(Cancion cancion){
        this.repository.guardarCancion(cancion);
    }

    public List<Cancion> buscarCancionesPorPalabra(String palabra){
       return this.repository.obtenerCanciones().stream()
                        .filter(c -> c.getTitulo().toLowerCase().contains(palabra.toLowerCase()))
                        .toList();
    }

}
