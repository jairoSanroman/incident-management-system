package com.jairo.incidencias.mapper;

import com.jairo.incidencias.dto.IncidenciaDTO;
import com.jairo.incidencias.entity.Incidencia;
import com.jairo.incidencias.entity.EstadoIncidencia;

public class IncidenciaMapper {

    public static IncidenciaDTO toDTO(Incidencia incidencia) {
        IncidenciaDTO dto = new IncidenciaDTO();
        dto.setId(incidencia.getId());
        dto.setTitulo(incidencia.getTitulo());
        dto.setDescripcion(incidencia.getDescripcion());
        dto.setEstado(incidencia.getEstado());
        return dto;
    }

}