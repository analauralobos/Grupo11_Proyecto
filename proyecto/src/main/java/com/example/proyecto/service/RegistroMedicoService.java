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

        System.out.println("=== registrarAtencion INICIO ===");
        System.out.println("Datos recibidos:");
        System.out.println("idTurno: " + rm.getIdTurno());
        System.out.println("idMedico: " + rm.getIdMedico());
        System.out.println("idHistoriaClinica: " + rm.getIdHistoriaClinica());
        System.out.println("Diagnostico: " + rm.getDiagnostico());
        System.out.println("Tratamiento: " + rm.getTratamiento());
        System.out.println("Estudios: " + rm.getEstudios());
    
        // Obtener turno
        Turno turno = turnoDAO.obtenerPorId(rm.getIdTurno());
        if (turno == null) {
            System.out.println("Turno no encontrado con id: " + rm.getIdTurno());
            throw new RuntimeException("El turno no existe");
        }
        System.out.println("Turno encontrado: " + turno);
    
        // Validar estado del turno
        if (!turno.getIdEstado().equals(1)) {
            System.out.println("Estado del turno no es válido: " + turno.getIdEstado());
            throw new RuntimeException("El turno no está disponible para registrar atención");
        }
        System.out.println("Estado del turno OK: " + turno.getIdEstado());
    
        // Validar campos obligatorios
        if (rm.getDiagnostico() == null || rm.getDiagnostico().isEmpty()) {
            System.out.println("Falta diagnóstico");
            throw new RuntimeException("Debe ingresar el diagnóstico");
        }
        if (rm.getTratamiento() == null || rm.getTratamiento().isEmpty()) {
            System.out.println("Falta tratamiento");
            throw new RuntimeException("Debe ingresar el tratamiento");
        }
    
        System.out.println("Creando registro médico...");
        RegistroMedico nuevoRegistro = dao.crear(rm);
        System.out.println("Registro médico creado: " + nuevoRegistro);
    
        // Actualizar estado del turno a Finalizado (id_estado = 3)
        System.out.println("Actualizando estado del turno a Finalizado...");
        turno.setIdEstado(3);
        turnoDAO.actualizar(turno);
        System.out.println("Turno actualizado: " + turno);
    
        System.out.println("=== registrarAtencion FIN ===");
        return nuevoRegistro;
    }
    
}
