package com.devsenior.democapas.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.devsenior.democapas.model.Cancion;

@Repository
public class CancionRepository {

    public List<Cancion> obtenerCanciones(){
        return List.of(new Cancion("Cancion del mundial"));
    }
}
