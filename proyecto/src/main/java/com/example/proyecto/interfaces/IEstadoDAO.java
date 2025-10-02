package com.example.proyecto.interfaces;

import com.example.proyecto.model.Estado;
import java.util.List;

public interface IEstadoDAO {
    List<Estado> obtenerTodos();
    Estado obtenerPorId(Integer id);
    Estado crear(Estado estado);
    Estado actualizar(Estado estado);
    void eliminar(Integer id);
}
