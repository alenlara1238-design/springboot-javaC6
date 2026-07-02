package com.devsenior.alara.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.devsenior.alara.model.SolicitudMentoria;
import com.devsenior.alara.repository.MentoriaRepository;

@Service
public class MentoriaService {
    private final MentoriaRepository repository;

    public MentoriaService(MentoriaRepository repository){
        this.repository = repository;
    }

    public List<SolicitudMentoria> obtenerTodos(){
        return repository.obtenerTodos();
    }

    public SolicitudMentoria crearSolicitud(SolicitudMentoria solicitud){
        solicitud.setEstado("PENDIENTE");
        return repository.guardar(solicitud);
    }

    public Optional<SolicitudMentoria> buscarPorId(Long id){
        return repository.obtenerTodos().stream()
                .filter(s -> s.getId().equals(id))
                .findFirst();
    }

    public List<SolicitudMentoria> filtrarPorTecnologia(String tecnologia){
        return repository.obtenerTodos().stream()
                .filter(s -> s.getTecnologia().equalsIgnoreCase(tecnologia))
                .toList();
    }

    public Optional<SolicitudMentoria> actualizarMentoria(Long id, SolicitudMentoria datos){
        List<SolicitudMentoria> lista = repository.obtenerTodos();

        return lista.stream()
                .filter(s -> s.getId().equals(id)) //busca y encuentra el objeto solitud con ese id
                .findFirst()
                .map(solicitudExistente -> {
                    int indice = lista.indexOf(solicitudExistente);
                    solicitudExistente.setEstudiante(datos.getEstudiante());
                    solicitudExistente.setTecnologia(datos.getTecnologia());
                    solicitudExistente.setEstado(datos.getEstado());
                    return repository.actualizar(indice, solicitudExistente);
                });
    }

    public boolean eliminarMentoria(Long id){
        return repository.obtenerTodos().stream()
                    .filter(s -> s.getId().equals(id))
                    .findFirst()
                    .map(s -> repository.eliminar(s))
                    .orElse(false);
    }


}
