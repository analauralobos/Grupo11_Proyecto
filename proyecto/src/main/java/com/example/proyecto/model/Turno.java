package com.example.proyecto.model;

import lombok.Data;
import java.time.LocalDateTime;
import java.sql.Time;

@Data
public class Turno {
    private Integer idTurno;
    private Integer idAgenda;
    private Integer dniPaciente;
    private LocalDateTime fechaTurno; 
    private Time horaTurno;
    private Integer idEstado;
    private String motivo;
}
