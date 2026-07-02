package com.devsenior.alara.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsenior.alara.model.SolicitudMentoria;
import com.devsenior.alara.service.MentoriaService;

@RestController
@RequestMapping("/api/mentoria")
public class MentoriaController {

    private final MentoriaService service;

    public MentoriaController(MentoriaService service){
        this.service = service;
    }

    @GetMapping
    public List<SolicitudMentoria> obtenerTodos(){
        return service.obtenerTodos();
    }

    @PostMapping
    public SolicitudMentoria crear(@RequestBody SolicitudMentoria solicitud){
       return service.crearSolicitud(solicitud);
    }


}
