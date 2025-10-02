package com.example.proyecto.interfaces;

import com.example.proyecto.model.Turno;
import java.util.List;

public interface ITurnoDAO {
    List<Turno> obtenerTodos();
    Turno obtenerPorId(Integer id);
    Turno crear(Turno t);
    Turno actualizar(Turno t);
    void eliminar(Integer id);
}
