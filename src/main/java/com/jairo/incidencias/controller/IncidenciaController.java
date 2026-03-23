package com.jairo.incidencias.controller;

import com.jairo.incidencias.dto.IncidenciaDTO;
import com.jairo.incidencias.entity.Incidencia;
import com.jairo.incidencias.mapper.IncidenciaMapper;
import org.springframework.web.bind.annotation.*;
import com.jairo.incidencias.service.IncidenciaService;
import java.util.List;
import java.util.Optional;
import com.jairo.incidencias.entity.EstadoIncidencia;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@RestController
@RequestMapping("/incidencias")
public class IncidenciaController {
    private final IncidenciaService incidenciaService;



    public IncidenciaController(IncidenciaService incidenciaService) {
        this.incidenciaService = incidenciaService;
    }

    // Obtener todas las incidencias
    @GetMapping
    public List<Incidencia> obtenerIncidencias() {
        return incidenciaService.obtenerTodas();
    }

    // Obtener incidencia por ID
    @GetMapping("/{id}")
    public Optional<Incidencia> obtenerPorId(@PathVariable Long id) {
        return incidenciaService.obtenerPorId(id);
    }

    // Crear nueva incidencia
    @PostMapping
    public Incidencia crear(@Valid @RequestBody IncidenciaDTO dto) {
        Incidencia incidencia = IncidenciaMapper.toEntity(dto);

        return incidenciaService.guardar(incidencia);
    }

    // Actualizar incidencia
    @PutMapping("/{id}")
    public Incidencia actualizarIncidencia(@PathVariable Long id, @RequestBody Incidencia incidencia) {
        return incidenciaService.actualizar(id, incidencia);
    }

    // Eliminar incidencia
    @DeleteMapping("/{id}")
    public void eliminarIncidencia(@PathVariable Long id) {
        incidenciaService.eliminar(id);
    }

    // Buscar incidencias por estado
    @GetMapping("/estado/{estado}")
    public Page<IncidenciaDTO> obtenerPorEstado(
            @PathVariable EstadoIncidencia estado,
            Pageable pageable) {

        return incidenciaService.obtenerPorEstado(estado, pageable);
    }
    // Buscar con filtros dinámicos
    @GetMapping("/buscar")
    public Page<Incidencia> buscar(
            @RequestParam(required = false) EstadoIncidencia estado,
            @RequestParam(required = false) String titulo,
            Pageable pageable) {

        return incidenciaService.buscar(estado, titulo, pageable);
    }

}
