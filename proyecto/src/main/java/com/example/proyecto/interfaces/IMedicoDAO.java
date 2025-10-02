package com.example.proyecto.interfaces;

import com.example.proyecto.model.Medico;
import java.util.List;

public interface IMedicoDAO {
    List<Medico> obtenerTodos();
    Medico obtenerPorId(Integer dni);
    Medico crear(Medico medico);
    Medico actualizar(Medico medico);
    void eliminar(Integer dni);
}
