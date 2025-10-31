package com.example.proyecto.service;

import com.example.proyecto.dao.HistoriaClinicaDAO;
import com.example.proyecto.model.HistoriaClinica;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HistoriaClinicaService {
    private final HistoriaClinicaDAO historiaClinicaDao;

    public List<HistoriaClinica> obtenerTodos() {
        return historiaClinicaDao.obtenerTodos();
    }

    public HistoriaClinica obtenerPorId(Integer id) {
        return historiaClinicaDao.obtenerPorId(id);
    }

    public HistoriaClinica crear(HistoriaClinica h) {
        return historiaClinicaDao.crear(h);
    }

    public HistoriaClinica actualizar(HistoriaClinica h) {
        return historiaClinicaDao.actualizar(h);
    }

    public void eliminar(Integer id) {
        historiaClinicaDao.eliminar(id);
    }
}
