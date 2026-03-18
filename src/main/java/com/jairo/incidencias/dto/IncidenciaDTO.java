package com.jairo.incidencias.dto;

import lombok.Data;


@Data
public class IncidenciaDTO {

    private Long id;
    private String titulo;
    private String descripcion;
    private String estado;
}