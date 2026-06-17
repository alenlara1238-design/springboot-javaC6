package com.devsenior.democapas.service;

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

}
