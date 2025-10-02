package com.example.proyecto.model;

import lombok.Data;

@Data
public class RegistroMedico {
    private Integer idRegistroMedico;
    private Integer idTurno;
    private Integer idMedico;
    private Integer idHistoriaClinica;
    private String diagnostico;
    private String tratamiento;
    private String estudios;
}
