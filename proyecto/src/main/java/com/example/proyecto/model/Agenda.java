package com.example.proyecto.model;

import lombok.Data;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
public class Agenda {
    private Integer idAgenda;
    private Integer dniMedico;
    private LocalDateTime fechaAtencion;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private String estadoAgenda;
    private String descripcion;
    private String ubicacion;
}
