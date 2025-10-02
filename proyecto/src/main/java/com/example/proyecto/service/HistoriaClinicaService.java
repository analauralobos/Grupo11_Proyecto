package com.example.proyecto.service;

import com.example.proyecto.dao.HistoriaClinicaDAO;
import com.example.proyecto.model.HistoriaClinica;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HistoriaClinicaService {
    private final HistoriaClinicaDAO dao;

    public List<HistoriaClinica> obtenerTodos() {
        return dao.obtenerTodos();
    }

    public HistoriaClinica obtenerPorId(Integer id) {
        return dao.obtenerPorId(id);
    }

    public HistoriaClinica crear(HistoriaClinica h) {
        return dao.crear(h);
    }

    public HistoriaClinica actualizar(HistoriaClinica h) {
        return dao.actualizar(h);
    }

    public void eliminar(Integer id) {
        dao.eliminar(id);
    }
}
