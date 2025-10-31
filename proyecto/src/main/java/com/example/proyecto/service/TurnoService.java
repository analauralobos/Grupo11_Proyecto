package com.example.proyecto.service;

import com.example.proyecto.dao.TurnoDAO;
import com.example.proyecto.model.Turno;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.time.LocalTime;


@Service
@RequiredArgsConstructor
public class TurnoService {

    private final TurnoDAO turnoDao;
    private final PacienteService pacienteService;

    public List<Turno> obtenerTodos() {
        return turnoDao.obtenerTodos();
    }

    public Turno obtenerPorId(Integer id) {
        return turnoDao.obtenerPorId(id);
    }

    public Turno crear(Turno turno) {        
        // Validar paciente
        if (pacienteService.obtenerPorId(turno.getDniPaciente()) == null) {
            throw new RuntimeException("Paciente no registrado. Registre al paciente antes de agendar el turno.");
        }

        // Convertir LocalDate a String 
        String fechaStr = turno.getFechaTurno().toString(); 
        
        // Verificar si ya existe un turno para el mismo médico, fecha y hora
        Turno existente = turnoDao.obtenerPorMedicoFechaHora(
            turno.getDniMedico(),
            fechaStr,
            turno.getHoraTurno()
        );

        if (existente != null) {
            throw new RuntimeException("Ya existe un turno para este médico en esa fecha y hora.");
        }

        // Guardar turno
        turno.setIdEstado(1); // Reservado
        return turnoDao.crear(turno);
    }

    public Turno actualizar(Turno turno) {
        return turnoDao.actualizar(turno);
    }

    public void eliminar(Integer id) {
        turnoDao.eliminar(id);
    }
}
