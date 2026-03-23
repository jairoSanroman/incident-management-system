package com.jairo.incidencias.repository;

import com.jairo.incidencias.entity.Incidencia;
import com.jairo.incidencias.entity.EstadoIncidencia;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IncidenciaRepository extends JpaRepository<Incidencia, Long> {

    // Filtrar por estado
    Page<Incidencia> findByEstado(EstadoIncidencia estado, Pageable pageable);

    // Filtrar por título (contiene texto, sin importar mayúsculas)
    Page<Incidencia> findByTituloContainingIgnoreCase(String titulo, Pageable pageable);

    // Filtrar por estado + título
    Page<Incidencia> findByEstadoAndTituloContainingIgnoreCase(
            EstadoIncidencia estado,
            String titulo,
            Pageable pageable
    );
}

