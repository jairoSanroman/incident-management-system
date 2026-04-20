package com.jairo.incidencias.specification;

import com.jairo.incidencias.entity.Incidencia;
import com.jairo.incidencias.entity.EstadoIncidencia;
import org.springframework.data.jpa.domain.Specification;

public class IncidenciaSpecification {

    public static Specification<Incidencia> tieneEstado(EstadoIncidencia estado) {
        return (root, query, cb) -> {
            if (estado == null) return null;
            return cb.equal(root.get("estado"), estado);
        };
    }

    public static Specification<Incidencia> tituloContiene(String titulo) {
        return (root, query, cb) -> {
            if (titulo == null) return null;
            return cb.like(cb.lower(root.get("titulo")), "%" + titulo.toLowerCase() + "%");
        };
    }
}