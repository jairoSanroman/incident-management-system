package com.jairo.incidencias.repository;

import com.jairo.incidencias.entity.EstadoIncidencia;
import com.jairo.incidencias.entity.Incidencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
public interface IncidenciaRepository extends JpaRepository<Incidencia, Long> {
    Page<Incidencia> findByEstado(EstadoIncidencia estado, Pageable pageable);
}

