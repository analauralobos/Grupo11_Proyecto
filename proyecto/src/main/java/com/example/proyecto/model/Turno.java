package com.example.proyecto.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Turno {

    @JsonProperty("id_turno")
    private Integer idTurno;

    @JsonProperty("id_agenda")
    private Integer idAgenda;

    @JsonProperty("dni_paciente")
    private String dniPaciente;

    @JsonProperty("fecha_turno")
    private String fechaTurno; // formato: "2025-10-01T08:34:00"

    @JsonProperty("hora_turno")
    private String horaTurno;  // formato: "08:34:00"

    @JsonProperty("id_estado")
    private Integer idEstado;

    
    private String motivo;
}
