package com.example.proyecto.interfaces;

import com.example.proyecto.model.HistoriaClinica;
import java.util.List;

public interface IHistoriaClinicaDAO {
    List<HistoriaClinica> obtenerTodos();
    HistoriaClinica obtenerPorId(Integer id);
    HistoriaClinica crear(HistoriaClinica hc);
    HistoriaClinica actualizar(HistoriaClinica hc);
    void eliminar(Integer id);
}
