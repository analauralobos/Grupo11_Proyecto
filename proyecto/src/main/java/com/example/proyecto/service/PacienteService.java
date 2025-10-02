package com.example.proyecto.service;

import com.example.proyecto.dao.PacienteDAO;
import com.example.proyecto.model.Paciente;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PacienteService {
    private final PacienteDAO dao;

    public List<Paciente> obtenerTodos() {
        return dao.obtenerTodos();
    }

    public Paciente obtenerPorId(Integer dni) {
        return dao.obtenerPorId(dni);
    }

    public Paciente crear(Paciente paciente) {
        return dao.crear(paciente);
    }

    public Paciente actualizar(Paciente paciente) {
        return dao.actualizar(paciente);
    }

    public void eliminar(Integer dni) {
        dao.eliminar(dni);
    }
}
