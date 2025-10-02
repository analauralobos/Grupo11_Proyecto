package com.example.proyecto.model;

import lombok.Data;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
public class Agenda {
    private Integer idAgenda;
    private Integer dniMedico;
    private String fechaAtencion; // "2025-10-01T08:00:00"
    private String horaInicio;    // "08:00:00"
    private String horaFin;       // "10:00:00"
    private String estadoAgenda;
    private String descripcion;
    private String ubicacion;
}
