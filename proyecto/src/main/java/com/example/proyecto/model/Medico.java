package com.example.proyecto.model;

import lombok.Data;

@Data
public class Medico {
    private Integer dniMedico;
    private String matricula;
    private String nombre;
    private String apellido;
    private String especialidad;
    private String telefono;
    private String email;
    private String diasAtencion;
    private String horaInicio;
    private String horaFin;
}
