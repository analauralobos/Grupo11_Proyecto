package com.example.proyecto.model;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class HistoriaClinica {
    private Integer idHistoriaClinica;    
    private Integer dniPaciente;
    private String antecedentes; 
    private String alergias;    
}
