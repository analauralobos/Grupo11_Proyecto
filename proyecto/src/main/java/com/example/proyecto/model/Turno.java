package com.example.proyecto.model;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class Turno {
    private Integer idTurno;
    private Integer dniPaciente;
    private Integer dniMedico;
    private LocalDateTime fechaTurno; 
    private String horaTurno;
    private Integer idEstado;
    private String motivo;
}
