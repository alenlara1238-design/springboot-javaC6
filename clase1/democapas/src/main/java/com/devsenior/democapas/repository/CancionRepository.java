package com.devsenior.democapas.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.devsenior.democapas.model.Cancion;

@Repository
public class CancionRepository {

    private final List<Cancion> canciones = new ArrayList<>(List.of(
        new Cancion("Cancion 1"),
        new Cancion("Titulo diferente"),
        new Cancion("Encuentro de 2")
    ));

    public List<Cancion> obtenerCanciones(){
        return canciones;
    }

    public void guardarCancion(Cancion cancion){
        this.canciones.add(cancion);
    }


}
