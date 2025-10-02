package com.example.proyecto.model;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class HistoriaClinica {
    private Integer idHistoriaClinica;
    private LocalDateTime fechaCreacion;
    private String alergia;
    private String antecedente;
    private String medicacionActual;
    private String observacion;
    private LocalDateTime ultimaActualizacion;
    private Integer dniPaciente;
}
