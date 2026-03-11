package com.jairo.incidencias.repository;

import com.jairo.incidencias.entity.Incidencia;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface IncidenciaRepository extends JpaRepository<Incidencia, Long> {
    // Buscar incidencias por estado
    List<Incidencia> findByEstado(String estado);
}