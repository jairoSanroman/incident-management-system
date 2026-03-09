package com.jairo.incidencias.controller;

import com.jairo.incidencias.entity.Incidencia;
import com.jairo.incidencias.repository.IncidenciaRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/incidencias")
public class IncidenciaController {

    private final IncidenciaRepository repository;

    public IncidenciaController(IncidenciaRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Incidencia> obtenerTodas() {
        return repository.findAll();
    }

    @PostMapping
    public Incidencia crear(@RequestBody Incidencia incidencia) {
        return repository.save(incidencia);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        repository.deleteById(id);
    }
}