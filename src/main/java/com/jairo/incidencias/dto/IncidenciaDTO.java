package com.jairo.incidencias.dto;

import lombok.Data;
import com.jairo.incidencias.entity.EstadoIncidencia;

@Data
public class IncidenciaDTO {

    private Long id;
    private String titulo;
    private String descripcion;
    private EstadoIncidencia estado;
}