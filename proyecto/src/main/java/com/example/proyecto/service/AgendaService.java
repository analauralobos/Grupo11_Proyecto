package com.example.proyecto.service;

import com.example.proyecto.dao.AgendaDAO;
import com.example.proyecto.model.Agenda;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AgendaService {
    private final AgendaDAO dao;

    public List<Agenda> obtenerTodos() {
        return dao.obtenerTodos();
    }

    public Agenda obtenerPorId(Integer id) {
        return dao.obtenerPorId(id);
    }

    public Agenda crear(Agenda agenda) {
        return dao.crear(agenda);
    }

    public Agenda actualizar(Agenda agenda) {
        return dao.actualizar(agenda);
    }

    public void eliminar(Integer id) {
        dao.eliminar(id);
    }
}
