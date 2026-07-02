package com.devsenior.alara.model;

public class SolicitudMentoria {
    private Long id;
    private String estudiante;
    private String tecnologia; //ejemplo: "Java", "Angular",...
    private String estado; // ejemplo: "pendiente", "resuelta"

    public SolicitudMentoria() {} //consturctor vacío que es usado por Spring boot

    public SolicitudMentoria(Long id, String estudiante, String tecnologia, String estado) {
        this.id = id;
        this.estudiante = estudiante;
        this.tecnologia = tecnologia;
        this.estado = estado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(String estudiante) {
        this.estudiante = estudiante;
    }

    public String getTecnologia() {
        return tecnologia;
    }

    public void setTecnologia(String tecnologia) {
        this.tecnologia = tecnologia;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    

    



}
