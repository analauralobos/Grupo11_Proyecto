package com.example.proyecto.service;

import com.example.proyecto.dao.TurnoDAO;
import com.example.proyecto.model.Turno;
import com.example.proyecto.model.Agenda;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TurnoService {

    private final TurnoDAO dao;
    private final PacienteService pacienteService;
    private final AgendaService agendaService;

    public List<Turno> obtenerTodos() {
        return dao.obtenerTodos();
    }

    public Turno obtenerPorId(Integer id) {
        return dao.obtenerPorId(id);
    }

    public Turno crear(Turno turno) {        
        // Validar paciente
        Integer dniPacienteInt = Integer.parseInt(turno.getDniPaciente());        
        if (pacienteService.obtenerPorId(dniPacienteInt) == null) {
            throw new RuntimeException("Paciente no registrado. Registre al paciente antes de agendar el turno.");
        }
        
        // Validar agenda
        Integer idAgenda = turno.getIdAgenda();         
        Agenda agenda = agendaService.obtenerPorId(idAgenda); 
        if (agenda == null) {
            throw new RuntimeException("No existe la agenda solicitada.");
        }
        if (!"Disponible".equalsIgnoreCase(agenda.getEstadoAgenda())) {
            throw new RuntimeException("No hay horarios disponibles.");
        }

        // Validar disponibilidad del turno (recien agregado)
        if (dao.existeTurnoEnHorario(turno.getIdAgenda(), turno.getFechaTurno(), turno.getHoraTurno())) {
        throw new RuntimeException("Turno no disponible. Ya existe un turno en ese horario.");
        }

        // Guardar turno
        turno.setIdEstado(1); // Reservado
        return dao.crear(turno);
    }

    public Turno actualizar(Turno turno) {
        return dao.actualizar(turno);
    }

    public void eliminar(Integer id) {
        dao.eliminar(id);
    }
}
