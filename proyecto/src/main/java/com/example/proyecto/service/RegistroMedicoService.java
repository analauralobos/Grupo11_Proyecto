package com.example.proyecto.service;

import com.example.proyecto.dao.RegistroMedicoDAO;
import com.example.proyecto.model.RegistroMedico;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RegistroMedicoService {
    private final RegistroMedicoDAO dao;

    public List<RegistroMedico> obtenerTodos() {
        return dao.obtenerTodos();
    }

    public RegistroMedico obtenerPorId(Integer id) {
        return dao.obtenerPorId(id);
    }

    public RegistroMedico crear(RegistroMedico rm) {
        return dao.crear(rm);
    }

    public RegistroMedico actualizar(RegistroMedico rm) {
        return dao.actualizar(rm);
    }

    public void eliminar(Integer id) {
        dao.eliminar(id);
    }
}
