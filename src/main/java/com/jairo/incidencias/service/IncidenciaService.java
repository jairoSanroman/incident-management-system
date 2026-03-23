package com.jairo.incidencias.service;

import com.jairo.incidencias.dto.IncidenciaDTO;
import com.jairo.incidencias.entity.EstadoIncidencia;
import com.jairo.incidencias.entity.Incidencia;
import com.jairo.incidencias.mapper.IncidenciaMapper;
import com.jairo.incidencias.repository.IncidenciaRepository;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

@Service
public class IncidenciaService {
    // Repositorio para acceder a la base de datos
    private final IncidenciaRepository incidenciaRepository;

    public IncidenciaService(IncidenciaRepository incidenciaRepository) {
        this.incidenciaRepository = incidenciaRepository;
    }
    // Obtener todas las incidencias
    public List<Incidencia> obtenerTodas() {
        return incidenciaRepository.findAll();
    }
    // Obtener una incidencia por ID
    public Optional<Incidencia> obtenerPorId(Long id) {
        return incidenciaRepository.findById(id);
    }
    // Guardar una nueva incidencia
    public Incidencia guardar(Incidencia incidencia) {
        return incidenciaRepository.save(incidencia);
    }
    // Actualizar una incidencia existente
    public Incidencia actualizar(Long id, Incidencia nuevaIncidencia) {
        return incidenciaRepository.findById(id)
                .map(incidencia -> {
                    incidencia.setTitulo(nuevaIncidencia.getTitulo());
                    incidencia.setDescripcion(nuevaIncidencia.getDescripcion());
                    incidencia.setEstado(nuevaIncidencia.getEstado());
                    return incidenciaRepository.save(incidencia);
                })
                .orElse(null);
    }
    // Eliminar una incidencia
    public void eliminar(Long id) {
        incidenciaRepository.deleteById(id);
    }
    public Page<IncidenciaDTO> obtenerPorEstado(EstadoIncidencia estado, Pageable pageable) {
        return incidenciaRepository
                .findByEstado(estado, pageable)
                .map(IncidenciaMapper::toDTO);
    }
    // 🔥 MÉTODO DE BÚSQUEDA DINÁMICA
    public Page<Incidencia> buscar(EstadoIncidencia estado, String titulo, Pageable pageable) {

        // Ambos filtros
        if (estado != null && titulo != null) {
            return incidenciaRepository
                    .findByEstadoAndTituloContainingIgnoreCase(estado, titulo, pageable);
        }

        // Solo estado
        if (estado != null) {
            return incidenciaRepository.findByEstado(estado, pageable);
        }

        // Solo título
        if (titulo != null) {
            return incidenciaRepository
                    .findByTituloContainingIgnoreCase(titulo, pageable);
        }

        // Sin filtros
        return incidenciaRepository.findAll(pageable);
    }
}