package com.jairo.incidencias.controller;

import com.jairo.incidencias.entity.Incidencia;
import org.springframework.web.bind.annotation.*;
import com.jairo.incidencias.service.IncidenciaService;
import java.util.List;
import java.util.Optional;

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
    public Incidencia crearIncidencia(@RequestBody Incidencia incidencia) {
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
    public List<Incidencia> obtenerPorEstado(@PathVariable String estado) {
        return incidenciaService.obtenerTodas()
                .stream()
                .filter(i -> estado.equalsIgnoreCase(i.getEstado()))
                .toList();
    }
}