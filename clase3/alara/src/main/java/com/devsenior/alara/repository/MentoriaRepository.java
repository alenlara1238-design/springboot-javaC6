package com.devsenior.alara.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.devsenior.alara.model.SolicitudMentoria;

@Repository
public class MentoriaRepository {
    private final List<SolicitudMentoria> db = new ArrayList<>();
    private long secuenciadorId = 1;

    public MentoriaRepository() {
        db.add(new SolicitudMentoria(secuenciadorId++, "Ana", "Java", "PENDIENTE"));
        db.add(new SolicitudMentoria(secuenciadorId++, "Juan", "Spring Boot", "ACEPTADA"));
        db.add(new SolicitudMentoria(secuenciadorId++, "Maria", "Python", "PENDIENTE"));
        db.add(new SolicitudMentoria(secuenciadorId++, "Pedro", "JavaScript", "RESUELTA"));
    }

    public List<SolicitudMentoria> obtenerTodos(){
        return db;
    }

    public SolicitudMentoria guardar(SolicitudMentoria solicitud){
        solicitud.setId(secuenciadorId++);
        db.add(solicitud);
        return solicitud;
    }

    public SolicitudMentoria actualizar(int indice, SolicitudMentoria solicitud){
        db.set(indice, solicitud);
        return solicitud;
    }

    public boolean eliminar(SolicitudMentoria solicitud){
       return db.remove(solicitud);
    }
}
