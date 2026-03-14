package com.jairo.incidencias.mapper;

import com.jairo.incidencias.dto.IncidenciaDTO;
import com.jairo.incidencias.entity.Incidencia;
import com.jairo.incidencias.entity.EstadoIncidencia;

public class IncidenciaMapper {

    public static Incidencia toEntity(IncidenciaDTO dto) {

        Incidencia incidencia = new Incidencia();

        incidencia.setTitulo(dto.getTitulo());
        incidencia.setDescripcion(dto.getDescripcion());
        incidencia.setEstado(EstadoIncidencia.valueOf(dto.getEstado()));

        return incidencia;
    }

}