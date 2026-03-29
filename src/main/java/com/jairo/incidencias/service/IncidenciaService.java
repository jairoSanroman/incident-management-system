package com.jairo.incidencias.service;

import com.jairo.incidencias.dto.IncidenciaDTO;
import com.jairo.incidencias.entity.EstadoIncidencia;
import com.jairo.incidencias.entity.Incidencia;
import com.jairo.incidencias.mapper.IncidenciaMapper;
import com.jairo.incidencias.repository.IncidenciaRepository;
import com.jairo.incidencias.specification.IncidenciaSpecification;

import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

@Service
public class IncidenciaService {

    private final IncidenciaRepository incidenciaRepository;

    public IncidenciaService(IncidenciaRepository incidenciaRepository) {
        this.incidenciaRepository = incidenciaRepository;
    }

    // Obtener todas (puedes pasar a DTO si quieres ser consistente)
    public List<Incidencia> obtenerTodas() {
        return incidenciaRepository.findAll();
    }

    // Obtener por ID (ya bien 💯)
    public Incidencia obtenerPorId(Long id) {
        return incidenciaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Incidencia no encontrada"));
    }

    // Guardar
    public Incidencia guardar(Incidencia incidencia) {
        return incidenciaRepository.save(incidencia);
    }

    // 🔥 ACTUALIZAR (MEJORADO)
    public Incidencia actualizar(Long id, Incidencia nuevaIncidencia) {
        Incidencia incidencia = incidenciaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Incidencia no encontrada"));

        incidencia.setTitulo(nuevaIncidencia.getTitulo());
        incidencia.setDescripcion(nuevaIncidencia.getDescripcion());
        incidencia.setEstado(nuevaIncidencia.getEstado());

        return incidenciaRepository.save(incidencia);
    }

    // 🔥 ELIMINAR (MEJORADO)
    public void eliminar(Long id) {
        if (!incidenciaRepository.existsById(id)) {
            throw new RuntimeException("Incidencia no encontrada");
        }
        incidenciaRepository.deleteById(id);
    }

    // 🔥 POR ESTADO → DTO (perfecto)
    public Page<IncidenciaDTO> obtenerPorEstado(EstadoIncidencia estado, Pageable pageable) {
        return incidenciaRepository
                .findByEstado(estado, pageable)
                .map(IncidenciaMapper::toDTO);
    }

    // 🔥 BÚSQUEDA DINÁMICA (TOP nivel)
    public Page<IncidenciaDTO> buscar(EstadoIncidencia estado, String titulo, Pageable pageable) {

        Specification<Incidencia> spec = Specification.allOf(
                IncidenciaSpecification.tieneEstado(estado),
                IncidenciaSpecification.tituloContiene(titulo)
        );

        return incidenciaRepository.findAll(spec, pageable)
                .map(IncidenciaMapper::toDTO);
    }
}