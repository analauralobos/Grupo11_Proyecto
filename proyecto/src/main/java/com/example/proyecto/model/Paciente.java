package com.example.proyecto.model;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class Paciente {
    private Integer dniPaciente;
    private String nombre;
    private String apellido;
    private String obraSocial;
    private LocalDateTime fechaNacimiento;
    private String telefono;
    private String email;
   
}
