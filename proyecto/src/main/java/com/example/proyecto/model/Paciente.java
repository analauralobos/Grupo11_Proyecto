package com.example.proyecto.model;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class Paciente {
    private Integer dni_paciente;
    private String nombre;
    private String apellido;
    private String obra_social;
    private LocalDateTime fecha_nacimiento;
    private String telefono;
    private String email;
   
}
