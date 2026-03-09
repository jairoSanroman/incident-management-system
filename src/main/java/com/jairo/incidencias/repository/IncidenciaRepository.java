package com.jairo.incidencias.repository;

import com.jairo.incidencias.entity.Incidencia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IncidenciaRepository extends JpaRepository<Incidencia, Long> {

}