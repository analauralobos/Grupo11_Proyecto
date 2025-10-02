package com.example.proyecto.interfaces;

import com.example.proyecto.model.Paciente;
import java.util.List;

public interface IPacienteDAO {
    List<Paciente> obtenerTodos();
    Paciente obtenerPorId(Integer dni);
    Paciente crear(Paciente paciente);
    Paciente actualizar(Paciente paciente);
    void eliminar(Integer dni);
}
