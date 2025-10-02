package com.example.proyecto.interfaces;

import com.example.proyecto.model.Agenda;
import java.util.List;

public interface IAgendaDAO {
    List<Agenda> obtenerTodos();
    Agenda obtenerPorId(Integer id);
    Agenda crear(Agenda agenda);
    Agenda actualizar(Agenda agenda);
    void eliminar(Integer id);
}
