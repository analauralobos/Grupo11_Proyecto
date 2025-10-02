package com.example.proyecto.service;

import com.example.proyecto.dao.EstadoDAO;
import com.example.proyecto.model.Estado;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EstadoService {
    private final EstadoDAO dao;

    public List<Estado> obtenerTodos() {
        return dao.obtenerTodos();
    }

    public Estado obtenerPorId(Integer id) {
        return dao.obtenerPorId(id);
    }

    public Estado crear(Estado e) {
        return dao.crear(e);
    }

    public Estado actualizar(Estado e) {
        return dao.actualizar(e);
    }

    public void eliminar(Integer id) {
        dao.eliminar(id);
    }
}
