package com.jairo.incidencias.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Incidencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String descripcion;
    private String estado;

}
