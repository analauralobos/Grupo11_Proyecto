package com.example.proyecto.service;

import com.example.proyecto.dao.TurnoDAO;
import com.example.proyecto.model.Turno;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

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
        System.out.println("###dniPaciente:: " + turno.getDniPaciente());
        // Validar paciente
        if (pacienteService.obtenerPorId(turno.getDniPaciente()) == null) {
            throw new RuntimeException("Paciente no registrado. Registre al paciente antes de agendar el turno.");
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
