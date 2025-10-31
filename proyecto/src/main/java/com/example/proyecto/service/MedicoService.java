package com.example.proyecto.service;

import com.example.proyecto.dao.MedicoDAO;
import com.example.proyecto.dao.TurnoDAO;
import com.example.proyecto.model.Medico;
import com.example.proyecto.model.Turno;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class MedicoService {
    private final MedicoDAO medicoDao;
    private final TurnoDAO turnoDao;

    public List<Medico> obtenerTodos() {
        return medicoDao.obtenerTodos();
    }

    public Medico obtenerPorId(Integer dni) {
        return medicoDao.obtenerPorId(dni);
    }

    public Medico crear(Medico medico) {
        return medicoDao.crear(medico);
    }

    public Medico actualizar(Medico medico) {
        return medicoDao.actualizar(medico);
    }

    public void eliminar(Integer dni) {
        medicoDao.eliminar(dni);
    }

    //---Ver Agenda---
    public List<Turno> verAgenda(Integer dniMedico, LocalDate fecha) {
        // 1. Verificar que el médico exista
        Medico medico = medicoDao.obtenerPorId(dniMedico);
        if (medico == null) {
            throw new RuntimeException("No existe un médico con DNI: " + dniMedico);
        }
    
        // 2. Obtener el día de la semana en español
        String[] dias = {"Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo"};
        String diaSemana = dias[fecha.getDayOfWeek().getValue() - 1];
    
        // 3. Verificar si el médico trabaja ese día
        
        if (medico.getDiasAtencion() == null || !medico.getDiasAtencion().contains(diaSemana)) {
            throw new RuntimeException("El médico no atiende los días " + diaSemana);
        }
    
        // 4. Convertir LocalDate a String y llamar al DAO
        String fechaStr = fecha.toString(); // formato "yyyy-MM-dd"
        List<Turno> turnos = turnoDao.obtenerPorMedicoYFecha(dniMedico, fechaStr);
    
        // 5. Retornar la lista de turnos
        return turnos;
    }
    
    
}
