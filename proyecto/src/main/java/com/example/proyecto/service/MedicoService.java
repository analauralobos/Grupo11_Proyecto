package com.example.proyecto.service;

import com.example.proyecto.dao.MedicoDAO;
import com.example.proyecto.model.Medico;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MedicoService {
    private final MedicoDAO dao;

    public List<Medico> obtenerTodos() {
        return dao.obtenerTodos();
    }

    public Medico obtenerPorId(Integer dni) {
        return dao.obtenerPorId(dni);
    }

    public Medico crear(Medico medico) {
        return dao.crear(medico);
    }

    public Medico actualizar(Medico medico) {
        return dao.actualizar(medico);
    }

    public void eliminar(Integer dni) {
        dao.eliminar(dni);
    }
}
