package com.example.proyecto.service;

import com.example.proyecto.dao.RegistroMedicoDAO;
import com.example.proyecto.dao.TurnoDAO;
import com.example.proyecto.model.RegistroMedico;
import com.example.proyecto.model.Turno;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RegistroMedicoService {

    private final RegistroMedicoDAO dao;
    private final TurnoDAO turnoDAO;

    public List<RegistroMedico> obtenerTodos() {
        return dao.obtenerTodos();
    }

    public RegistroMedico obtenerPorId(Integer id) {
        return dao.obtenerPorId(id);
    }

    public RegistroMedico crear(RegistroMedico rm) {
        return dao.crear(rm);
    }

    public RegistroMedico actualizar(RegistroMedico rm) {
        return dao.actualizar(rm);
    }

    public void eliminar(Integer id) {
        dao.eliminar(id);
    }

    
    public RegistroMedico registrarAtencion(RegistroMedico rm) {
        
        Turno turno = turnoDAO.obtenerPorId(rm.getIdTurno());
        if (turno == null) {            
            throw new RuntimeException("El turno no existe");
        }
        
        if (!turno.getIdEstado().equals(1)) {            
            throw new RuntimeException("El turno no está disponible para registrar atención");
        }
        
        if (rm.getDiagnostico() == null || rm.getDiagnostico().isEmpty()) {           
            throw new RuntimeException("Debe ingresar el diagnóstico");
        }
        if (rm.getTratamiento() == null || rm.getTratamiento().isEmpty()) {            
            throw new RuntimeException("Debe ingresar el tratamiento");
        }   
        
        RegistroMedico nuevoRegistro = dao.crear(rm);
        
        turno.setIdEstado(3);
        turnoDAO.actualizar(turno);
        
        return nuevoRegistro;
    }
    
}
