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
        System.out.println("Turno recibido: " + turno);
        // Validar paciente
        Integer dniPacienteInt = Integer.parseInt(turno.getDniPaciente());
        System.out.println("dnipaciente: " + dniPacienteInt);
        if (pacienteService.obtenerPorId(dniPacienteInt) == null) {
            throw new RuntimeException("Paciente no registrado. Registre al paciente antes de agendar el turno.");
        }
        // Validar agenda
        Integer idAgenda = turno.getIdAgenda(); 
        System.out.println("idAgenda: " + idAgenda);
        Agenda agenda = agendaService.obtenerPorId(idAgenda);
        System.out.println("agenda: " + agenda);
        if (agenda == null || !"Disponible".equalsIgnoreCase(agenda.getEstadoAgenda())) {
            throw new RuntimeException("No hay horarios disponibles para este médico en la fecha seleccionada.");
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
