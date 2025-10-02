package com.example.proyecto.model;

import lombok.Data;

@Data
public class Medico {
    private Integer dniMedico;
    private String nombre;
    private String apellido;
    private String especialidad;
    private Integer telefono;
    private String email;
}
